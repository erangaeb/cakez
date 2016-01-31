package com.pagero.cakez.handlers

import com.pagero.cakez.exceptions.{InvalidEmployeeId, InvalidEmployeeInput}
import com.pagero.cakez.protocols.{User, Employee}
import com.pagero.cakez.components.{UserServiceComp, EmployeeDbComp}
import org.slf4j.LoggerFactory

import scala.util.Try

/**
 * Handle user entering employee details from here,
 * We do
 *    1. Create employee
 *    2. Find employees
 *
 * @author eranga bandara(erangaeb@gmail.com)
 */
class EmployeeHandler {

  // dependencies via self typed annotation
  this: EmployeeDbComp with UserServiceComp =>

  def logger = LoggerFactory.getLogger(this.getClass)

  /**
   * Handles employee creation functionality with use of EmployeeDbComp and UserServiceComp
   * We mainly do
   *    1. Validate input
   *    2. Create employee in the database
   *    3. Upload employee to the colud service
   * @param inputEmp user input
   * @return created employee
   */
  def createEmployee(inputEmp: String): Employee = {
    // inputEmp comes as 'emp_id name department'
    val tokens = inputEmp.split(" ")

    // validate input content
    if (tokens.length != 3) {
      logger.error(s"Invalid input: ${inputEmp}, employee should contains [id name department]")
      throw InvalidEmployeeInput("Invalid input, employee should contains [id name department]")
    }

    // validate emp_id
    if (!Try(tokens(0).toInt).isSuccess) {
      logger.error(s"Invalid employee ID: ${tokens(0)}")
      throw InvalidEmployeeId("Invalid employee ID " + tokens(0))
    }

    val employee = Employee(tokens(0).toInt, tokens(1), tokens(2))

    // create employee via db
    employeeDb.createEmployee(employee)

    // create User from employee
    // POST employee to server
    val user = User(None, "test@gmail.com", Some(employee.empId), employee.name, employee.department)
    userService.POST(user)

    employee
  }

  /**
   * Handles employee find with the use of EmployeeDbComp and UserServiceComp
   * @param empId employee ID
   * @return employee
   */
  def findEmployee(empId: Int): Employee = {
    // validate empId
    if (empId == 0)
      throw InvalidEmployeeId("Invalid employee ID " + empId)

    // find employee via db
    employeeDb.getEmployee(empId)
  }

}
