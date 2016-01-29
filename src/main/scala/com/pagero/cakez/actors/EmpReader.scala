package com.pagero.cakez.actors

import akka.actor.Actor
import com.pagero.cakez.db.CakezCassandraCluster
import com.pagero.cakez.handlers.UserHandler
import com.pagero.cakez.services.{CassandraUserDbCompImpl, SprayUserServiceCompImpl}

case class InitReader()

/**
 * Created by eranga on 1/9/16.
 */
class EmpReader extends Actor {

  override def preStart = {
    println("----started----- " + context.self.path)
  }

  // employee handler dependencies
  trait EmployeeHandlerConfig extends SprayUserServiceCompImpl with CassandraUserDbCompImpl with CakezCassandraCluster

  val employeeHandler = new UserHandler with EmployeeHandlerConfig

  override def receive: Receive = {
    case InitReader => {
      // listen for user inputs via commandline
      while (true) {
        println("--------------------------------------------")
        println("#Employee(id, name, department)")
        println("--------------------------------------------")

        val inputEmployee = scala.io.StdIn.readLine()

        if (!inputEmployee.isEmpty) {
          // handle employee via Employee handler
          employeeHandler.createUser()
        }
      }
    }
  }
}
