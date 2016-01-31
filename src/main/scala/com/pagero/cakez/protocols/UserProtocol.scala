package com.pagero.cakez.protocols

import spray.json.DefaultJsonProtocol

// Web service response Meta object
case class Meta(limit: Int, next: Option[String], offset: Int, previous: Option[String], total_count: Int)

// Web service response user
case class User(dob: Option[String], email: String, id: Option[Int], name: String, role: String)

// Meta + List of Users
case class UserResponse(meta: Meta, objects: List[User])

// User protocol to marshalling and un-marshalling
// Required by Spray
object UserProtocol extends DefaultJsonProtocol {
  implicit val MetaFormat = jsonFormat(Meta, "limit", "next", "offset", "previous", "total_count")
  implicit val UserFormat = jsonFormat(User, "dob", "email", "id", "name", "role")
  implicit val UserResponseFormat = jsonFormat(UserResponse, "meta", "objects")
}
