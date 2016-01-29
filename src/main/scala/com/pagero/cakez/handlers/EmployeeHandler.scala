package com.pagero.cakez.handlers

import com.pagero.cakez.exceptions.{InvalidEmployeeId, InvalidEmployeeInput}
import com.pagero.cakez.protocols.Employee
import com.pagero.cakez.services.EmployeeDbComp

import scala.util.Try

/**
 * Created by eranga on 1/28/16.
 */
class EmployeeHandler {

  this: EmployeeDbComp =>

  def createEmployee(inputEmp: String): Employee = {
    // empDetails comes as emp_id, name, department
    val tokens = inputEmp.split(" ")

    // validate input content
    if (tokens.length != 3) throw InvalidEmployeeInput("Invalid input. Employee should contains (id, name, department)")

    // validate emp_id
    if (!Try(tokens(0).toInt).isSuccess) throw InvalidEmployeeId("Invalid employee ID " + tokens(0))

    val employee = Employee(tokens(0).toInt, tokens(1), tokens(2))

    // create employee via db
    employeeDb.createEmployee(employee)

    // TODO upload employee to server

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
