package com.pagero.cakez.services

import com.pagero.cakez.actors.CakezActorSystem
import com.pagero.cakez.config.Configuration
import com.pagero.cakez.protocols.User
import org.slf4j.LoggerFactory
import spray.client.pipelining._
import spray.httpx.SprayJsonSupport._

import scala.concurrent.Future

/**
 * Created by eranga on 1/28/16.
 */
trait SprayUserServiceCompImpl extends UserServiceComp with Configuration {

  this: CakezActorSystem =>

  val userService = new SprayUserService

  class SprayUserService extends UserService {

    def logger = LoggerFactory.getLogger(this.getClass)

    override def POST(user: User): Future[Unit] = {
      import system.dispatcher
      import com.pagero.cakez.protocols.UserProtocol._

      logger.debug(s"POST user with id: ${user.id} name: ${user.name}")

      val pipeline = sendReceive
      val response = pipeline {
        Post(s"http://$apiHost:$apiPort/api/v1/users/", user)
      }

      response.map(_.entity.asInstanceOf[Unit])
    }

    override def GET(id: Int): Future[User] = {
      import system.dispatcher
      import com.pagero.cakez.protocols.UserProtocol._

      logger.debug("GET user with id: " + id)

      val pipeline = sendReceive ~> unmarshal[User]
      val response: Future[User] = pipeline {
        Get(s"http://$apiHost:$apiPort/api/v1/users/$id/?format=json")
      }

      response
    }
  }

}