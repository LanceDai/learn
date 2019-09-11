name := "sbt_learn"

version := "0.1"

scalaVersion := "2.12.8"
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "latest.milestone" % Test,
  "org.scalacheck" %% "scalacheck" % "latest.milestone" % Test
)
