package com.pagero.cakez.services

import com.pagero.cakez.protocols.User

import scala.concurrent.Future

/**
 * User Service component which wraps UserService
 * We have used cake pattern in here. This component used as a dependency in
 * EmployeeHandler
 *
 * @author eranga bandara(erangaeb@gmail.com)
 */
trait UserServiceComp {

  // entry point to UserService
  val userService: UserService

  /**
   * Actual UserService related functions are in here
   */
  trait UserService {
    /**
     * Upload user/employee to cloud via REST API,
     * Sends HTTP POST request in here
     * @param user User object
     * @return future
     */
    def POST(user: User): Future[Unit]

    /**
     * Find employee with given ID from the cloud(via REST API)
     * Sends HTTP GET request in here
     * @param id user ID
     * @return future
     */
    def GET(id: Int): Future[User]
  }

}
