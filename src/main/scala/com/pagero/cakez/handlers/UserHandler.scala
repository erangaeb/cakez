package com.pagero.cakez.handlers

import com.pagero.cakez.services.{UserDbComp, UserServiceComp}

/**
 * Created by eranga on 1/28/16.
 */
class UserHandler {

  this: UserServiceComp with UserDbComp =>

  def getUser(id: Int) = {
    // get user via UserService
    userService.GET(id) match {
      case Some(user) =>
        println(user)
      case _ =>
        println("error")
    }

    // save user in db
    //userDb.createUser()

    // logic + validation etc

  }

  def createUser() = {
    // logic + validation etc

    // create user via UserService

  }

}
