name := "ScalaSandbox"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)

scalacOptions += "-feature"