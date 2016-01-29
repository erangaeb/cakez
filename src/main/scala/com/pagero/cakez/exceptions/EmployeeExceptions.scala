package com.pagero.cakez.exceptions

/**
 * Created by eranga on 1/29/16.
 */
case class InvalidEmployeeInput(msg: String) extends Exception(msg)

case class InvalidEmployeeId(msg: String) extends Exception(msg)

