# Scala cake pattern demo

## Application functionality

  Application deals with `Employee(emp_id: Int, name: String, department: String)` objects. Following are the main functions

##### 1. Read employees

  Enter employees via commandline. `InputReader` actor handles this function

##### 2. Handler input employees

  `EmployeeHandler` handles the rest of the functionality

## Cake pattern dependencies

##### 1. EmployeeDbComp  

  `EmployeeHandler` has dependency with `EmployeeDbComp` which deals with saving employee detains in the database. We are using 
  `cassandra` database to store employee records. `CassandraEmployeeDbCompImpl` handles cassandra related implementation

##### 2. UserServiceComp  

  `EmployeeHandler` has dependency to `UserServiceComp` with uploads the employee to service via REST API. We have Spray based 
  UserServiceComp(`SprayUserServiceCompImpl`) 

## Cassandra config

##### 1. Create key space cakez

  ```
  create keyspace cakez with replication = {'class':'SimpleStrategy','replication_factor':1};
  ```

##### 2. Create table employee

  ```
  create table employee(emp_id Int primary key, name text, department text);
  ```

##### 3. Create index for name

  ```
  create index name_index on employee(name);
  ```
