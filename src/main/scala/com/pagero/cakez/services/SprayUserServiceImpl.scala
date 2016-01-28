package com.pagero.cakez.services

import scala.concurrent.Future

/**
 * Created by eranga on 1/28/16.
 */
trait SprayUserServiceCompImpl extends UserServiceComp {

  val userService = new SprayUserService

  class SprayUserService extends UserService {
    override def GET(id: Int): Future[User] = {
      Future[User(1, "era")]
    }

    override def POST(user: User): Future[Unit] = {

    }
  }

}
