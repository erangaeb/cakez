package com.pagero.cakez.services

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.QueryBuilder._
import com.pagero.cakez.db.CakezCassandraCluster
import com.pagero.cakez.protocols.Employee

/**
 * Created by eranga on 1/28/16.
 */
trait CassandraEmployeeDbCompImpl extends EmployeeDbComp {

  this: CakezCassandraCluster =>

  val employeeDb = new CassandraEmployeeDb

  class CassandraEmployeeDb extends EmployeeDb {
    override def getEmployee(empId: Int): Employee = {
      val selectStmt = select().all()
        .from("employee")
        .where(QueryBuilder.eq("emp_id", empId))
        .limit(1)

      val resultSet = session.execute(selectStmt)
      val row = resultSet.one()

      Employee(row.getInt("emp_id"), row.getString("name"), row.getString("department"))
    }

    override def createEmployee(employee: Employee) = {
      session.execute(s"INSERT INTO employee (emp_id, name, department) VALUES (${employee.empId}, '${employee.name}', '${employee.department}');")
    }
  }

}
