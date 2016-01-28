package com.pagero.cakez.protocols

import spray.json.DefaultJsonProtocol

case class Meta(limit: Int, next: Option[String], offset: Int, previous: Option[String], total_count: Int)

case class User(dob: Option[String], email: String, id: Option[Int], name: String, role: String)

case class UserResponse(meta: Meta, objects: List[User])

object UserProtocol extends DefaultJsonProtocol {
  implicit val MetaFormat = jsonFormat(Meta, "limit", "next", "offset", "previous", "total_count")
  implicit val UserFormat = jsonFormat(User, "dob", "email", "id", "name", "role")
  implicit val UserResponseFormat = jsonFormat(UserResponse, "meta", "objects")
}
