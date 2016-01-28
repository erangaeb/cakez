package com.pagero.cakez.services

import com.pagero.cakez.protocols.User

/**
 * Created by eranga on 1/28/16.
 */
trait UserDbComp {

  val userDb: UserDb

  trait UserDb {
    def getUser(id: Int): User

    def createUser(user: User)

    def deleteUser(user: User)
  }

}
