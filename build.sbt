ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

organization := "io.vigg"
publishMavenStyle := true
githubOwner := "Muelleau"
githubRepository := "skloom"
githubTokenSource := TokenSource.GitConfig("github.token")

resolvers += Resolver.githubPackages("OWNER")

lazy val skloom = (project in file("."))
  .settings(
    name := "skloom",
    version := "0.2.0-SNAPSHOT",
    crossPaths := false,
    libraryDependencies ++= Seq(
      "junit" % "junit" % "4.13.2" % Test,
      "org.scalatest" %% "scalatest" % "3.2.12" % Test,
      "com.novocode" % "junit-interface" % "0.11" % Test,
      "org.eclipse.jetty" % "jetty-server" % "11.0.9",
      "com.lihaoyi" %% "requests" % "0.7.0"
    )
  )
