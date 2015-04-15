import sbt._
import sbt.Keys._
import sbt.Project.projectToRef

import com.typesafe.sbt.web.SbtWeb
import com.typesafe.sbt.jse.JsEngineImport.JsEngineKeys

import com.typesafe.sbt.digest.Import._
import com.typesafe.sbt.gzip.Import._
import com.typesafe.sbt.less.Import._
import com.typesafe.sbt.rjs.Import._
import com.typesafe.sbt.web.Import._

import sbtbuildinfo.Plugin._

import play.Play.autoImport._

import playscalajs.PlayScalaJS.autoImport._

import com.sksamuel.scapegoat.sbt.ScapegoatSbtPlugin.autoImport._
import net.virtualvoid.sbt.graph.Plugin.graphSettings
import com.typesafe.sbt.SbtScalariform.{ScalariformKeys, defaultScalariformSettings}

object Server {
  private[this] val dependencies = {
    import Dependencies._
    Seq(
      Database.jdub, Database.postgresJdbc,
      WebJars.requireJs, WebJars.bootstrap,
      Testing.akkaTestkit,
      filters,
      Metrics.metrics, Metrics.metricsHealthChecks, Metrics.metricsJson,
      Metrics.metricsJvm, Metrics.jettyServlet, Metrics.metricsServlets, Metrics.metricsGraphite
    )
  }

  private[this] lazy val serverSettings = Seq(
    name := Shared.projectId,
    version := Shared.Versions.app,
    scalaVersion := Shared.Versions.scala,

    scalacOptions ++= Shared.compileOptions,
    scalacOptions in Test ++= Seq("-Yrangepos"),

    libraryDependencies ++= dependencies,

    doc in Compile <<= target.map(_ / "none"),

    scalaJSProjects := Seq(Client.client),

    // sbt-web
    pipelineStages := Seq(scalaJSProd, rjs, digest, gzip),
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
  ) ++ graphSettings ++ defaultScalariformSettings

  lazy val server = Project(
    id = Shared.projectId,
    base = file(".")
  )
    .enablePlugins(SbtWeb)
    .enablePlugins(play.PlayScala)
    .settings(buildInfoSettings: _*)
    .settings(serverSettings: _*)
    .aggregate(projectToRef(Client.client))
    .dependsOn(Shared.sharedJvm)
}
