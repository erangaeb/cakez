package com.pagero.cakez.handlers

import com.pagero.cakez.services.UserServiceComp

/**
 * Created by eranga on 1/28/16.
 */
class UserHandler {

  this: UserServiceComp =>

  def getUser(id: Int) = {
    // get user via UserService
    userService.GET(id) match {
      case Some(user) =>
        println(user)
      case _ =>
        println("error")
    }

    // logic + validation etc

  }

  def createUser() = {
    // logic + validation etc

    // create user via UserService

  }

}
