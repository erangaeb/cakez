name := "cakez"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= {

  val akkaVersion       = "2.3.9"
  val sprayVersion      = "1.3.2"
  val cassandraVersion  = "2.1.9"

  Seq(
    "com.typesafe.akka"       %% "akka-actor"               % akkaVersion,
    "com.typesafe.akka"       %% "akka-slf4j"               % akkaVersion,
    "io.spray"                %% "spray-can"                % sprayVersion,
    "io.spray"                %% "spray-routing"            % sprayVersion,
    "io.spray"                %% "spray-client"             % sprayVersion,
    "io.spray"                %% "spray-json"               % sprayVersion,
    "com.datastax.cassandra"  % "cassandra-driver-core"     % cassandraVersion
  )
}

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Spray repository" at "http://repo.spray.io"
)
