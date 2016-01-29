package com.pagero.cakez.actors

import akka.actor.Actor
import com.pagero.cakez.db.CakezCassandraCluster
import com.pagero.cakez.exceptions.{InvalidEmployeeInput, InvalidEmployeeId}
import com.pagero.cakez.handlers.EmployeeHandler
import com.pagero.cakez.protocols.Employee
import com.pagero.cakez.services.{CassandraEmployeeDbCompImpl, SprayUserServiceCompImpl}

case class InitReader()

/**
 * Created by eranga on 1/9/16.
 */
class InputReader extends Actor {

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
        handleInput(inputEmp)
      }
    }
  }

  def handleInput(inputEmp: String) = {
    // handle employee via Employee handler
    try {
      employeeHandler.createEmployee(inputEmp) match {
        case Employee(id, name, _) =>
          println("Employee created with name " + name)
        case _ =>
          println("Employee creation failed")
      }
    } catch {
      case e: InvalidEmployeeInput =>
        println(e.msg)
      case e: InvalidEmployeeId =>
        println(e.msg)
    }
  }
}