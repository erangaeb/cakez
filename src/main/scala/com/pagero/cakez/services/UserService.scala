package com.pagero.cakez.services

import scala.concurrent.Future

case class User(id: Int, username: String)

/**
 * Created by eranga on 1/28/16.
 */
trait UserServiceComp {

  val userService: UserService

  trait UserService {
    def GET(id: Int): Future[User]

    def POST(user: User): Future[Unit]
  }

}
