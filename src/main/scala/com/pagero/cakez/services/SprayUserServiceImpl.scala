package com.pagero.cakez.services

import com.pagero.cakez.actors.CakezActorSystem
import com.pagero.cakez.config.Configuration
import com.pagero.cakez.protocols.User
import org.slf4j.LoggerFactory
import spray.client.pipelining._
import spray.httpx.SprayJsonSupport._

import scala.concurrent.Future

/**
 * Spray based UserServiceComp implementation
 *
 * @author eranga bandara(erangaeb@gmail.com)
 */
trait SprayUserServiceCompImpl extends UserServiceComp with Configuration {

  // passing actor system with self typed annotation
  this: CakezActorSystem =>

  val userService = new SprayUserService

  /**
   * Spray based UserService implementation
   * Actual HTTP POST, GET functions implemented in here(spray based requests)
   */
  class SprayUserService extends UserService {

    def logger = LoggerFactory.getLogger(this.getClass)

    /**
     * Send HTTP POST request to user API exists in the cloud
     * @param user User object
     * @return future
     */
    override def POST(user: User): Future[Unit] = {
      import system.dispatcher
      import com.pagero.cakez.protocols.UserProtocol._

      logger.debug(s"POST user with id: ${user.id} name: ${user.name}")

      // POST request with marshaled user
      val pipeline = sendReceive
      val response = pipeline {
        Post(s"http://$apiHost:$apiPort/api/v1/users/", user)
      }

      response.map(_.entity.asInstanceOf[Unit])
    }

    /**
     * Send HTTP GET request to user API in the cloud
     * @param id user ID
     * @return future
     */
    override def GET(id: Int): Future[User] = {
      import system.dispatcher
      import com.pagero.cakez.protocols.UserProtocol._

      logger.debug("GET user with id: " + id)

      // GET request and un-marshal response
      val pipeline = sendReceive ~> unmarshal[User]
      val response: Future[User] = pipeline {
        Get(s"http://$apiHost:$apiPort/api/v1/users/$id/?format=json")
      }

      response
    }
  }

}