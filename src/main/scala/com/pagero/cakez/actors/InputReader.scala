package com.pagero.cakez.actors

import akka.actor.Actor
import com.pagero.cakez.db.CakezCassandraCluster
import com.pagero.cakez.exceptions.{InvalidEmployeeId, InvalidEmployeeInput}
import com.pagero.cakez.handlers.EmployeeHandler
import com.pagero.cakez.protocols.Employee
import com.pagero.cakez.services.{CassandraEmployeeDbCompImpl, SprayUserServiceCompImpl}
import org.slf4j.LoggerFactory

case class InitReader()

/**
 * Created by eranga on 1/9/16.
 */
class InputReader extends Actor {

  def logger = LoggerFactory.getLogger(this.getClass)

  override def preStart = {
    logger.debug(s"Starting Actor ${context.self.path}")
  }

  // employee handler dependencies
  trait EmployeeHandlerConfig extends SprayUserServiceCompImpl with CakezActorSystem with CassandraEmployeeDbCompImpl with CakezCassandraCluster

  val employeeHandler = new EmployeeHandler with EmployeeHandlerConfig

  override def receive: Receive = {
    case InitReader => {
      while (true) {
        println("\n--------------------------------------------")
        println("#Employee(id, name, department)")
        println("--------------------------------------------")

        val inputEmp = scala.io.StdIn.readLine()

        logger.debug(s"Read input employee ${inputEmp}")

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
      case e: Throwable =>
        println(e.getStackTrace)
    }
  }
}