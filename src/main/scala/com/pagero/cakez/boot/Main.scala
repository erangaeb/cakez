package com.pagero.cakez.boot

import com.pagero.cakez.handlers.UserHandler
import com.pagero.cakez.services.{CassandraUserDbCompImpl, SprayUserServiceCompImpl}

/**
 * Created by eranga on 1/28/16.
 */
object Main extends App {

  // default usage
  trait DefaultConfig extends SprayUserServiceCompImpl with CassandraUserDbCompImpl

  val defaultUserHandler = new UserHandler with DefaultConfig
  defaultUserHandler.getUser(1)

  // test usage
  trait TestConfig extends SprayUserServiceCompImpl with CassandraUserDbCompImpl

  val testUserHandler = new UserHandler with TestConfig
  testUserHandler.getUser(1)
}
