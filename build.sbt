ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val skloom = (project in file("."))
  .settings(
    name := "skloom",
    crossPaths := false,
    libraryDependencies ++= Seq(
      "junit" % "junit" % "4.13.2" % Test,
      "org.scalatest" %% "scalatest" % "3.2.11" % Test,
      "com.novocode" % "junit-interface" % "0.11" % Test,
      "com.lihaoyi" %% "requests" % "0.7.0"
    )
  )
