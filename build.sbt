import io.gatling.sbt.GatlingPlugin

val scala_version = "2.11.8"
val akka_version ="2.3.7"
val gatling_version = "2.1.7"

def gatling = "io.gatling" % "gatling-core" % gatling_version
def netty = "io.netty" % "netty" % "3.10.1.Final"
def akkaActor = "com.typesafe.akka" %% "akka-actor" % akka_version
def scalalogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
def scalaLibrary = "org.scala-lang" % "scala-library" % scala_version
def highcharts = "io.gatling.highcharts" % "gatling-charts-highcharts" % gatling_version % "test"
def gatlingtestframework = "io.gatling" % "gatling-test-framework" % gatling_version % "test"
def akkaTest = "com.typesafe.akka" %% "akka-testkit" % akka_version % "test"


lazy val root = (project in file(".")).
  settings(
    organization := "io.scalecube",
    version := (version in ThisBuild).value,
    scalaVersion := scala_version,
    name := "gatling-tcp-extension",
    libraryDependencies += gatling,
    libraryDependencies += netty,
    libraryDependencies += akkaActor,
    libraryDependencies += scalalogging,
    libraryDependencies += highcharts,
    libraryDependencies += gatlingtestframework,
    libraryDependencies += akkaTest,
    libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test",
    libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "test"
  )

enablePlugins(GatlingPlugin)

publishMavenStyle := true

publishTo := {
  val nexus = "https://sap-posco-admin.codecentric.de/nexus/"
  if (isSnapshot.value) {
    Some("snapshots" at nexus + "repository/maven-snapshots/")
  } else {
    Some("release" at nexus + "repository/maven-releases/")
  }
}


val nexusUrl = System.getenv("NEXUS_URL")
val nexusRepositoryPath = System.getenv("NEXUS_REPOSITORY_PATH")
val nexusUsername = System.getenv("NEXUS_USERNAME")
val nexusPassword = System.getenv("NEXUS_PASSWORD")

credentials := Seq(Credentials("Sonatype Nexus", nexusUrl, nexusUsername, nexusPassword))


publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := <url>https://github.com/scalecube/gatling-tcp-extensions</url>
  <licenses>
    <license>
      <name>The MIT License (MIT)</name>
      <url>https://opensource.org/licenses/MIT</url>
    </license>
  </licenses>
  <scm>
    <url>https://github.com/scalecube/gatling-tcp-extensions.git</url>
    <connection>scm:git:https://github.com/scalecube/gatling-tcp-extensions.git</connection>
  </scm>
  <developers>
    <developer>
      <id>myroslavlisniak</id>
      <name>Myroslav Lisniak</name>
    </developer>
  </developers>