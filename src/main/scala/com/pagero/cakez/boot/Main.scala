package com.pagero.cakez.boot

import com.pagero.cakez.handlers.UserHandler
import com.pagero.cakez.services.SprayUserServiceCompImpl

/**
 * Created by eranga on 1/28/16.
 */
object Main extends App {
  val handler = new UserHandler with SprayUserServiceCompImpl
  handler.getUser(1)
}
