package com.pagero.cakez.services

import com.pagero.cakez.protocols.Employee


/**
 * Employee DB Component which wraps Employee DB
 * We have used cake pattern in here. This component use as a dependency in
 * EmployeeHandler
 *
 * @author eranga bandara(erangaeb@gmail.com)
 */
trait EmployeeDbComp {

  // entry point to Employee DB
  val employeeDb: EmployeeDb

  /**
   * Actual Employee DB related functions are in here
   */
  trait EmployeeDb {
    /**
     * Create employee in the database
     * @param employee employee
     */
    def createEmployee(employee: Employee)

    /**
     *
     * @param empId Find employee which is matching to given employee ID
     * @return employee
     */
    def getEmployee(empId: Int): Employee
  }

}
