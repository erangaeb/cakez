package com.pagero.cakez.services

import com.pagero.cakez.protocols.User

/**
 * Created by eranga on 1/28/16.
 */
trait UserServiceComp {

  val userService: UserService

  trait UserService {
    def GET(id: Int): Option[User]

    def POST(user: User)
  }

}
