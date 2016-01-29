package com.pagero.cakez.actors

import akka.actor.Actor
import com.pagero.cakez.db.CakezCassandraCluster
import com.pagero.cakez.handlers.EmployeeHandler
import com.pagero.cakez.services.{CassandraEmployeeDbCompImpl, SprayUserServiceCompImpl}

case class InitReader()

/**
 * Created by eranga on 1/9/16.
 */
class EmpReader extends Actor {

  override def preStart = {
    println("----started----- " + context.self.path)
  }

  // employee handler dependencies
  trait EmployeeHandlerConfig extends SprayUserServiceCompImpl with CassandraEmployeeDbCompImpl with CakezCassandraCluster

  val employeeHandler = new EmployeeHandler with EmployeeHandlerConfig

  override def receive: Receive = {
    case InitReader => {
      // listen for user inputs via commandline
      while (true) {
        println("--------------------------------------------")
        println("#Employee(id, name, department)")
        println("--------------------------------------------")

        val inputEmp = scala.io.StdIn.readLine()

        if (!inputEmp.isEmpty) {
          // handle employee via Employee handler
          employeeHandler.createEmployee(inputEmp)
        }
      }
    }
  }
}
