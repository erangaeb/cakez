package com.pagero.cakez.components

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.QueryBuilder._
import com.pagero.cakez.db.CakezCassandraCluster
import com.pagero.cakez.protocols.Employee
import org.slf4j.LoggerFactory

/**
 * Apache cassandra based EmployeeDbComp implementation
 *
 * @author eranga bandara(erangaeb@gmail.com)
 */
trait CassandraEmployeeDbComp extends EmployeeDbComp {

  // We are passing Cassandra configuration via self typed annotation
  this: CakezCassandraCluster =>

  val employeeDb = new CassandraEmployeeDb

  /**
   * Apache cassandra based EmployeeDb implementation
   * Actual employee retrieve, insert functions implemented in here
   * @author eranga bandara(erangaeb@gmail.com)
   */
  class CassandraEmployeeDb extends EmployeeDb {

    def logger = LoggerFactory.getLogger(this.getClass)

    /**
     * Cassandra based database insert happens in here
     * Database session comes via CassandraEmployeeDb
     * @param employee employee
     */
    override def createEmployee(employee: Employee) = {
      logger.debug(s"Create employee with id: ${employee.empId} name: ${employee.name}")

      // insert query
      val statement = QueryBuilder.insertInto("employee")
        .value("emp_id", employee.empId)
        .value("name", employee.name)
        .value("department", employee.department)

      session.execute(statement)
    }

    /**
     * Cassandra based select happens in here
     * Database session comes via CassandraEmployeeDb
     * @param empId Find employee which is matching to given employee ID
     * @return employee
     */
    override def getEmployee(empId: Int): Employee = {
      logger.debug(s"get employee with ID: ${empId}")

      // select query
      val selectStmt = select().all()
        .from("employee")
        .where(QueryBuilder.eq("emp_id", empId))
        .limit(1)

      val resultSet = session.execute(selectStmt)
      val row = resultSet.one()

      Employee(row.getInt("emp_id"), row.getString("name"), row.getString("department"))
    }
  }

}
