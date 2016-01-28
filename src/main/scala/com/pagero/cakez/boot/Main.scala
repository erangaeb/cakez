package com.pagero.cakez.boot

import com.pagero.cakez.config.Configuration

/**
 * Created by eranga on 1/28/16.
 */
object Main extends App with Configuration {
  println(apiHost)
  println(apiPort)
}
