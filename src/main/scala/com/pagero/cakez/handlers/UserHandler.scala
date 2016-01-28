package com.pagero.cakez.handlers

import com.pagero.cakez.protocols.User
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

    // get user via db
    userDb.getUser(1)

    // logic + validation etc

  }

  def createUser() = {
    // save user in db
    userDb.createUser(User(None, "erangaeb@gmail.com", Some(1), "eranga", "USER"))

    // create user via UserService


    // logic + validation etc

  }

}
