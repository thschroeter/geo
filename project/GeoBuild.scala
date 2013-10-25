import sbt._
import sbt.Keys._

object PipelineBuild extends Build {
  
  lazy val geo = Project(
    id = "geo",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "geo",
      organization := "info.thschroeter",
      version := "0.1",
      scalaVersion := "2.10.3",
      scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8"),
      resolvers ++= Seq(
        "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases",
        "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
        "spray repo" at "http://repo.spray.io/",
        "spray nightly" at "http://nightlies.spray.io"),
      libraryDependencies ++= Seq(
        "com.typesafe.akka" %% "akka-actor" % "2.2.3",
        "com.typesafe.akka" %% "akka-testkit" % "2.2.3",
        "org.specs2" %% "specs2" % "2.2.3" % "test",
        "io.spray" % "spray-can" % "1.2-20131024",
        "io.spray" % "spray-routing" % "1.2-20131024",
        "io.spray" % "spray-testkit" % "1.2-20131024",
        "io.spray" %% "spray-json" % "1.2.5"
        )))
}
