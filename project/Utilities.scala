import Dependencies.{Akka, Metrics, Play}
import io.gatling.sbt.GatlingPlugin
import pl.project13.scala.sbt.JmhPlugin
import sbt.Keys._
import sbt._

object Utilities {
  lazy val benchmarking = (project in file("util/benchmarking")).settings(
    name := "benchmarking",
    libraryDependencies ++= Seq(Dependencies.Testing.gatlingCore, Dependencies.Testing.gatlingCharts)
  ).settings(Shared.commonSettings: _*).dependsOn(Shared.sharedJvm, Server.server).enablePlugins(GatlingPlugin, JmhPlugin)

  lazy val iconCreator = (project in file("util/iconCreator")).settings(
    name := "icon-creator"
  ).settings(Shared.commonSettings: _*)

  private[this] val metricsLibs = Seq(
    Play.playLib, Akka.actor,
    Metrics.metrics, Metrics.healthChecks, Metrics.json, Metrics.jvm, Metrics.ehcache, Metrics.jettyServlet, Metrics.servlets, Metrics.graphite
  )

  lazy val metrics = (project in file("util/metrics"))
    .settings(libraryDependencies ++= metricsLibs)
    .settings(Shared.commonSettings: _*)

  lazy val screenshotCreator = (project in file("util/screenshotCreator")).settings(
    name := "screenshot-creator"
  ).settings(Shared.commonSettings: _*).dependsOn(Shared.sharedJvm)
}
