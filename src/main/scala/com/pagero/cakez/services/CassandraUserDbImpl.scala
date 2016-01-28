package com.pagero.cakez.services

import com.pagero.cakez.db.CassandraCluster
import com.pagero.cakez.protocols.User

/**
 * Created by eranga on 1/28/16.
 */
trait CassandraUserDbCompImpl extends UserDbComp with CassandraCluster {

  val userDb = new CassandraUserDb

  class CassandraUserDb extends UserDb {
    override def getUser(id: Int): User = ???

    override def deleteUser(user: User): Unit = ???

    override def createUser(user: User): Unit = ???
  }

}
