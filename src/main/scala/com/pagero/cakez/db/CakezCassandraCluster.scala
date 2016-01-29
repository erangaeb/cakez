package com.pagero.cakez.db

import com.datastax.driver.core.{Cluster, Session}
import com.pagero.cakez.config.Configuration

/**
 * Created by eranga on 1/19/16.
 */
trait CakezCassandraCluster extends Configuration {
  lazy val cluster: Cluster = {
    Cluster.builder().
      addContactPoint("127.0.0.1").
      build()
  }

  lazy val session: Session = cluster.connect("dev")
}