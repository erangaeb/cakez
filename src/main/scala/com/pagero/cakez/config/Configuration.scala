package com.pagero.cakez.config

import com.typesafe.config.ConfigFactory

import scala.util.Try

/**
 * Load configurations define in application.conf from here
 *
 * @author eranga herath(erangaeb@gmail.com)
 */
trait Configuration {
  // config object
  val config = ConfigFactory.load()

  // api config
  lazy val apiHost = Try(config.getString("api.host")).getOrElse("localhost")
  lazy val apiPort = Try(config.getInt("api.port")).getOrElse(8000)

  // cassandra config
  lazy val cassandraHost = Try(config.getString("db.cassandra.host")).getOrElse("localhost")
  lazy val cassandraKeyspace = Try(config.getString("db.cassandra.keyspace")).getOrElse("cakez")
}
