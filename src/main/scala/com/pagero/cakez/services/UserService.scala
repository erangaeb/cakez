package com.pagero.cakez.services

import com.pagero.cakez.protocols.User

/**
 * Created by eranga on 1/28/16.
 */
trait UserServiceComp {

  val userService: UserService

  trait UserService {
    def POST(user: User)

    def GET(id: Int): Option[User]
  }

}
