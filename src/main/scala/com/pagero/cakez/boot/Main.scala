package com.pagero.cakez.boot

import akka.actor.Props
import com.pagero.cakez.actors.{CakezActorSystem, InitReader, InputReader}
import org.slf4j.LoggerFactory

/**
 * Created by eranga on 1/28/16.
 */
object Main extends App with CakezActorSystem {
  def logger = LoggerFactory.getLogger(this.getClass)

  logger.debug("Booting application")

  // start senz sender
  val senzSender = system.actorOf(Props(classOf[InputReader]), name = "InputRender")
  senzSender ! InitReader
}
