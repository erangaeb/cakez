package com.pagero.cakez.actors

import akka.actor.ActorSystem

/**
 * Created by eranga on 1/29/16.
 */
trait CakezActorSystem {
  implicit val system = ActorSystem("cakez")
}
