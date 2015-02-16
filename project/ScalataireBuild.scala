import sbt._
import Keys._
import com.typesafe.sbt.rjs.Import.rjs
import com.typesafe.sbt.packager.universal.UniversalKeys
import com.typesafe.sbt.web.SbtWeb
import com.typesafe.sbt.web.Import.{Assets, pipelineStages}
import com.typesafe.sbt.digest.Import.digest
import com.typesafe.sbt.gzip.Import.gzip
import com.typesafe.sbt.less.Import.LessKeys

object ScalataireBuild extends Build with UniversalKeys {
  val id = "scalataire"

  val dependencies = Seq(
    "nl.grons" %% "metrics-scala" % "3.3.0_a2.3",
    "org.eclipse.jetty" % "jetty-servlet" % "8.1.9.v20130131",
    "io.dropwizard.metrics" % "metrics-jvm" % "3.1.0",
    "io.dropwizard.metrics" % "metrics-graphite" % "3.1.0",
    "io.dropwizard.metrics" % "metrics-servlets" % "3.1.0",
    "org.webjars" % "requirejs" % "2.1.11-1"
  )

  private val compileOptions = Seq("-encoding", "UTF-8", "-feature", "-deprecation", "-unchecked")

  object Versions {
    val app = "0.1-SNAPSHOT"
    val scala = "2.11.5"
  }

  lazy val serverSettings = Seq(
    name := id,
    version := Versions.app,
    scalaVersion := Versions.scala,
    libraryDependencies ++= dependencies,
    pipelineStages := Seq(rjs, gzip),
    //pipelineStages := Seq(rjs, digest, gzip),
    includeFilter in (Assets, LessKeys.less) := "*.less",
    excludeFilter in (Assets, LessKeys.less) := "_*.less",
    scalacOptions ++= compileOptions
  )

  lazy val root = Project(
    id = id,
    base = file(".")
  ).enablePlugins(play.PlayScala, SbtWeb).settings(serverSettings: _*)
}
