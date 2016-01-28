package com.pagero.cakez.handlers

import com.pagero.cakez.services.{User, UserServiceComp}

import scala.util.{Failure, Success}

/**
 * Created by eranga on 1/28/16.
 */
class UserHandler {

  this: UserServiceComp =>

  def getUser(id: Int) = {
    // logic + validation etc

    // get user via UserService
    userService.GET(id) onComplete {
      case Success =>
        User(id, "Done")
      case Failure =>
        User(id, "Fail")
    }
  }

  def createUser(user: User) = {
    // logic + validation etc

    // create user via UserService

  }

}
