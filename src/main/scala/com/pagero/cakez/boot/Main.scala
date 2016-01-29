package com.pagero.cakez.boot

import akka.actor.Props
import com.pagero.cakez.actors.{CakezActorSystem, InitReader, InputReader}

/**
 * Created by eranga on 1/28/16.
 */
object Main extends App with CakezActorSystem {
  // start senz sender
  val senzSender = system.actorOf(Props(classOf[InputReader]), name = "InputRender")
  senzSender ! InitReader
}
