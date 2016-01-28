package com.pagero.cakez.boot

import com.pagero.cakez.handlers.UserHandler
import com.pagero.cakez.services.{CassandraUserDbCompImpl, SprayUserServiceCompImpl}

/**
 * Created by eranga on 1/28/16.
 */
object Main extends App {
  val handler = new UserHandler with SprayUserServiceCompImpl with CassandraUserDbCompImpl
  handler.getUser(1)
}
