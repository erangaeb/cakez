package com.pagero.cakez.services

import com.pagero.cakez.actors.CakezActorSystem
import com.pagero.cakez.config.Configuration
import com.pagero.cakez.protocols.User
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

    override def POST(user: User): Future[Unit] = {
      import system.dispatcher
      import com.pagero.cakez.protocols.UserProtocol._

      val pipeline = sendReceive
      val response = pipeline {
        Post("http://10.4.1.29:9000/api/v1/users/", user)
      }

      response.map(_.entity.asInstanceOf[Unit])
    }

    override def GET(id: Int): Future[User] = {
      import system.dispatcher
      import com.pagero.cakez.protocols.UserProtocol._

      val pipeline = sendReceive ~> unmarshal[User]
      val response: Future[User] = pipeline {
        Get(s"http://10.2.1.29:9000/api/v1/users/$id/?format=json")
      }

      response
    }
  }

}