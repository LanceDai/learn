name := "sbt_learn"

version := "0.1"

scalaVersion := "2.12.8"
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "latest.milestone" % Test,
  "org.scalacheck" %% "scalacheck" % "latest.milestone" % Test
)

resolvers += Resolver.url("artifactory",
  url("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"))
(Resolver.ivyStylePatterns)
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.8.4")