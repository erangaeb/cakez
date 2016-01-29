package com.pagero.cakez.boot

import akka.actor.{ActorSystem, Props}
import com.pagero.cakez.actors.{InputReader, InitReader}

/**
 * Created by eranga on 1/28/16.
 */
object Main extends App {

  // start actor to read inputs
  implicit val system = ActorSystem("cakez")

  // start senz sender
  val senzSender = system.actorOf(Props(classOf[InputReader]), name = "InputRender")
  senzSender ! InitReader
}
