package com.pagero.cakez.services

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorSystem}
import com.pagero.cakez.config.Configuration
import com.pagero.cakez.protocols.User
import spray.client.pipelining._
import spray.httpx.SprayJsonSupport._

import scala.concurrent.Future
import scala.util.{Failure, Success}

case class GetUser(id: Int)

/**
 * Created by eranga on 1/28/16.
 */
trait SprayUserServiceCompImpl extends UserServiceComp {

  val userService = new SprayUserService

  class SprayUserService extends UserService {
    implicit val system = ActorSystem()

    import system.dispatcher

    override def GET(id: Int): Option[User] = {
      import com.pagero.cakez.protocols.UserProtocol._

      val pipeline = sendReceive ~> unmarshal[User]

      val response: Future[User] = pipeline {
        Get(s"http://$apiHost:$apiPort/api/v1/users/$id/?format=json")
      }

      response onComplete {
        case Success(user) =>
          Some(user)
        case Failure(e) =>
          e.printStackTrace
      }

      None
    }

    override def POST(user: User) = {
      import com.pagero.cakez.protocols.UserProtocol._

      val pipeline = sendReceive

      val response = pipeline {
        Post(s"http://$apiHost:$apiPort/api/v1/users/", user)
      }
    }
  }

}

class SprayServiceActor extends Actor with Configuration {
  override def receive: Receive = {
    case GetUser(id) =>

    case _ =>

  }

  def getUser(id: Int) = {
    import com.pagero.cakez.protocols.UserProtocol._

    val pipeline = sendReceive ~> unmarshal[User]

    val response: Future[User] = pipeline {
      Get(s"http://$apiHost:$apiPort/api/v1/users/$id/?format=json")
    }

    response onComplete {
      case Success(user) =>
        Some(user)
      case Failure(e) =>
        e.printStackTrace
    }

    None
  }
}
