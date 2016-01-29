package com.pagero.cakez.services

import com.pagero.cakez.protocols.Employee


/**
 * Created by eranga on 1/28/16.
 */
trait EmployeeDbComp {

  val employeeDb: EmployeeDb

  trait EmployeeDb {
    def createEmployee(employee: Employee)

    def getEmployee(empId: Int): Employee
  }

}
