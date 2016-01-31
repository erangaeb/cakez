package com.pagero.cakez.boot

import akka.actor.Props
import com.pagero.cakez.actors.{CakezActorSystem, InitReader, InputReader}
import org.slf4j.LoggerFactory

/**
 * We do start InputReader actor from here
 *
 * @author eranga bandara(erangaeb@gmail.com)
 */
object Main extends App with CakezActorSystem {
  def logger = LoggerFactory.getLogger(this.getClass)

  logger.debug("Booting application")

  // start input reader actor
  val inputReader = system.actorOf(Props(classOf[InputReader]), name = "InputRender")
  inputReader ! InitReader
}
