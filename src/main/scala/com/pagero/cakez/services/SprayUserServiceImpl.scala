package com.pagero.cakez.services

import com.pagero.cakez.protocols.User
import spray.client.pipelining._

import scala.concurrent.Future
import spray.httpx.SprayJsonSupport._

/**
 * Created by eranga on 1/28/16.
 */
trait SprayUserServiceCompImpl extends UserServiceComp {

  val userService = new SprayUserService

  class SprayUserService extends UserService {
    override def GET(id: Int): Future[User] = {
      import com.pagero.cakez.protocols.UserProtocol._

      val pipeline = sendReceive ~> unmarshal[User]

      val response: Future[User] = pipeline {
        Get(s"http://192.168.1.20:9000/api/v1/users/$id/?format=json")
      }

      response
    }

    override def POST(user: User) = {
      import com.pagero.cakez.protocols.UserProtocol._

      val pipeline = sendReceive

      val response = pipeline {
        Post("http://192.168.1.20:9000/api/v1/users/", user)
      }
    }
  }

}
