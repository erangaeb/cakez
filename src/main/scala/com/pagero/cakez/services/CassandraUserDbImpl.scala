package com.pagero.cakez.services

import com.pagero.cakez.db.CassandraCluster
import com.pagero.cakez.protocols.User

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.QueryBuilder._

/**
 * Created by eranga on 1/28/16.
 */
trait CassandraUserDbCompImpl extends UserDbComp {

  this: CassandraCluster =>

  val userDb = new CassandraUserDb

  class CassandraUserDb extends UserDb {
    override def getUser(id: Int): User = {
      println("get.. user")
      val selectStmt = select().all()
        .from("agent")
        .where(QueryBuilder.eq("username", "eranga"))
        .limit(1)

      val resultSet = session.execute(selectStmt)
      val row = resultSet.one()

      println(row.getString("username") + " -------")
      User(None, "cassandrauser@gmail.com", Some(1), row.getString("username"), row.getString("branch"))
    }

    override def deleteUser(user: User): Unit = {
      println("delete.. user")
    }

    override def createUser(user: User): Unit = {
      println("create.. user")
      session.execute(s"INSERT INTO agent (username, branch) VALUES ('${user.name}', '${user.role}');")
    }
  }

}
