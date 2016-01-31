package com.pagero.cakez.exceptions

//Throws when user enters invalid employee from the commandline
case class InvalidEmployeeInput(msg: String) extends Exception(msg)

//Throws when having wrong employee ID field
case class InvalidEmployeeId(msg: String) extends Exception(msg)

