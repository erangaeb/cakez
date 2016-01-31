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
 * Actor class which handles reading inputs form the commandline
 * Starts to work on 'InitReader' message
 *
 * @author eranga bandara(erangaeb@gmail.com)
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
        println()
        println()
        println("--------------------------------------------")
        println("ENTER #Employee[id name department]")
        println("--------------------------------------------")
        println()

        val inputEmp = scala.io.StdIn.readLine()

        logger.debug(s"Read input employee ${inputEmp}")

        handleInput(inputEmp)
      }
    }
  }

  /**
   * Handle input message from here
   * Actual handler logic delegates to EmployeeHandler
   * @param inputEmp input string
   */
  def handleInput(inputEmp: String) = {
    // handle employee via Employee handler
    try {
      employeeHandler.createEmployee(inputEmp) match {
        case Employee(id, name, _) =>
          println("[DONE] employee created with name " + name)
        case _ =>
          println("[FAIL] employee creation failed")
      }
    } catch {
      case e: InvalidEmployeeInput =>
        logger.error("error ", e)
        println(s"[FAIL] ${e.msg}")
      case e: InvalidEmployeeId =>
        logger.error("error ", e)
        println(s"[FAIL] ${e.msg}")
      case e: Throwable =>
        logger.error("error ", e)
        println(e.getStackTrace)
    }
  }
}