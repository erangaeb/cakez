package com.pagero.cakez.services

import com.pagero.cakez.protocols.User

import scala.concurrent.Future

/**
 * Created by eranga on 1/28/16.
 */
trait UserServiceComp {

  val userService: UserService

  trait UserService {
    def POST(user: User): Future[Unit]

    def GET(id: Int): Future[User]
  }

}
