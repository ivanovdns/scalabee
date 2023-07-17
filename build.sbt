ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
  "org.apache.beam" % "beam-sdks-java-core" % "2.48.0",
  "org.apache.beam" % "beam-sdks-java-io-google-cloud-platform" % "2.48.0",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "com.github.sbt" % "junit-interface" % "0.13.3" % "test"
)

lazy val root = (project in file("."))
  .settings(
    name := "scalabee"
  )
