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

object ScalataireBuild extends Build with UniversalKeys {
  val projectId = "scalataire"

  val dependencies = {
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

  private val compileOptions = Seq("-encoding", "UTF-8", "-feature", "-deprecation", "-unchecked")

  object Versions {
    val app = "0.1-SNAPSHOT"
    val scala = "2.11.5"
  }

  lazy val serverSettings = Seq(
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
    buildInfoPackage := "utils"
  )

  lazy val root = Project(
    id = projectId,
    base = file(".")
  ).enablePlugins(SbtWeb).enablePlugins(play.PlayScala).settings(buildInfoSettings: _*).settings(serverSettings: _*)
}
