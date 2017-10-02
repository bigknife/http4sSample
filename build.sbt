import Dependencies._

scalaVersion in ThisBuild := "2.11.8"

lazy val sample = Project("sample", file("."))
  .enablePlugins(TomcatPlugin, JettyPlugin)
  .settings(
    libraryDependencies ++= libraries.sampleProject
  )
