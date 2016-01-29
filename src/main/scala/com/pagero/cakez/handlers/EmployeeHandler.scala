package com.pagero.cakez.handlers

import com.pagero.cakez.protocols.Employee
import com.pagero.cakez.services.EmployeeDbComp

/**
 * Created by eranga on 1/28/16.
 */
class EmployeeHandler {

  this: EmployeeDbComp =>

  def createEmployee(inputEmp: String): Boolean = {
    // empDetails comes as emp_id, name, department
    // split input and create employee
    val tokens = inputEmp.split(" ")
    val employee = Employee(tokens(1).toInt, tokens(1), tokens(2))

    // create employee via db
    employeeDb.createEmployee(employee)

    // upload employee to server

    true
  }

  def findEmployee(empId: Int): Employee = {
    // validate empId

    // find employee via db
    employeeDb.getEmployee(empId)
  }

}
