package com.pagero.cakez.handlers

import com.pagero.cakez.exceptions.{InvalidEmployeeId, InvalidEmployeeInput}
import com.pagero.cakez.protocols.{User, Employee}
import com.pagero.cakez.services.{UserServiceComp, EmployeeDbComp}
import org.slf4j.LoggerFactory

import scala.util.Try

/**
 * Created by eranga on 1/28/16
 */
class EmployeeHandler {

  this: EmployeeDbComp with UserServiceComp =>

  def logger = LoggerFactory.getLogger(this.getClass)

  def createEmployee(inputEmp: String): Employee = {
    // inputEmp comes as 'emp_id name department'
    val tokens = inputEmp.split(" ")

    // validate input content
    if (tokens.length != 3) {
      logger.error(s"Invalid input: ${inputEmp}, employee should contains (id, name, department)")
      throw InvalidEmployeeInput("Invalid input. Employee should contains (id, name, department)")
    }

    // validate emp_id
    if (!Try(tokens(0).toInt).isSuccess) {
      logger.error(s"Invalid employee ID: ${tokens(0)}")
      throw InvalidEmployeeId("Invalid employee ID " + tokens(0))
    }

    val employee = Employee(tokens(0).toInt, tokens(1), tokens(2))

    // create employee via db
    employeeDb.createEmployee(employee)

    // POST employee to server
    userService.POST(User(None, "serviceuser@gmail.com", Some(1), "service user", "USER"))

    employee
  }

  def findEmployee(empId: Int): Employee = {
    // validate empId
    if (empId == 0)
      throw InvalidEmployeeId("Invalid employee ID " + empId)

    // find employee via db
    employeeDb.getEmployee(empId)
  }

}
