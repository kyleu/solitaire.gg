import sbt._
import sbt.Keys._
import com.typesafe.sbt.packager.universal.UniversalKeys

import com.typesafe.sbt.web.SbtWeb
import com.typesafe.sbt.jse.JsEngineImport.JsEngineKeys

import com.typesafe.sbt.digest.Import._
import com.typesafe.sbt.gzip.Import._
import com.typesafe.sbt.less.Import._
import com.typesafe.sbt.rjs.Import._
import com.typesafe.sbt.web.Import._

import sbtbuildinfo.Plugin._

import play.Play.autoImport._

import com.sksamuel.scapegoat.sbt.ScapegoatSbtPlugin.autoImport._
import net.virtualvoid.sbt.graph.Plugin.graphSettings
import com.typesafe.sbt.SbtScalariform.{ScalariformKeys, defaultScalariformSettings}

object ScalataireBuild extends Build with UniversalKeys {
  val projectId = "scalataire"

  private[this] val dependencies = {
    import Dependencies._
    Seq(
      Database.jdub,
      Database.postgresJdbc,

      Metrics.metrics,
      Metrics.metricsJvm,
      Metrics.metricsHealthChecks,
      Metrics.metricsJson,
      Metrics.jettyServlet,
      Metrics.metricsServlets,
      Metrics.metricsGraphite,

      WebJars.requireJs,
      WebJars.bootstrap,

      Testing.akkaTestkit,

      filters
    )
  }

  private[this] val compileOptions = Seq(
    "-encoding", "UTF-8",
    "-feature", "-deprecation", "-unchecked",
    "–Xcheck-null", "-Xfatal-warnings", "-Xlint"
  )

  private[this] object Versions {
    val app = "0.1-SNAPSHOT"
    val scala = "2.11.6"
  }

  private[this] lazy val serverSettings = Seq(
    name := projectId,
    version := Versions.app,
    scalaVersion := Versions.scala,
    scalacOptions ++= compileOptions,
    scalacOptions in Test ++= Seq("-Yrangepos"),
    libraryDependencies ++= dependencies,
    doc in Compile <<= target.map(_ / "none"),

    // sbt-web
    pipelineStages := Seq(rjs, digest, gzip),
    includeFilter in (Assets, LessKeys.less) := "*.less",
    excludeFilter in (Assets, LessKeys.less) := "_*.less",
    JsEngineKeys.engineType := JsEngineKeys.EngineType.Node,

    // build info
    sourceGenerators in Compile <+= buildInfo,
    buildInfoKeys := Seq[BuildInfoKey](
      name,
      version,
      scalaVersion,
      sbtVersion,
      buildInfoBuildNumber,
      "builtAtMillis" -> System.currentTimeMillis,
      "builtAt" -> {
        val dtf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        dtf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"))
        dtf.format(new java.util.Date())
      }
    ),
    buildInfoPackage := "utils",

    // code quality
    scapegoatConsoleOutput := false,
    scapegoatIgnoredFiles := Seq(".*/routes_routing.scala", ".*/routes_reverseRouting.scala", ".*\\.template\\.scala"),
    scapegoatDisabledInspections := Seq("DuplicateImport"),
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  )

  lazy val root = Project(
    id = projectId,
    base = file(".")
  )
  .enablePlugins(SbtWeb)
  .enablePlugins(play.PlayScala)
  .settings(buildInfoSettings: _*)
  .settings(serverSettings: _*)
  .settings(graphSettings: _*)
  .settings(defaultScalariformSettings: _*)
}
