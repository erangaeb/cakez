package com.pagero.cakez.services

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.QueryBuilder._
import com.pagero.cakez.db.CakezCassandraCluster
import com.pagero.cakez.protocols.Employee
import org.slf4j.LoggerFactory

/**
 * Created by eranga on 1/28/16.
 */
trait CassandraEmployeeDbCompImpl extends EmployeeDbComp {

  this: CakezCassandraCluster =>

  val employeeDb = new CassandraEmployeeDb

  class CassandraEmployeeDb extends EmployeeDb {

    def logger = LoggerFactory.getLogger(this.getClass)

    override def createEmployee(employee: Employee) = {
      logger.debug(s"Create employee with id: ${employee.empId} name: ${employee.name}")

      val statement = QueryBuilder.insertInto("employee")
        .value("emp_id", employee.empId)
        .value("name", employee.name)
        .value("department", employee.department)

      session.execute(statement)
    }

    override def getEmployee(empId: Int): Employee = {
      logger.debug(s"get employee with ID: ${empId}")

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
