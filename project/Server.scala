import sbt._
import sbt.Keys._
import sbt.Project.projectToRef

import playscalajs.PlayScalaJS.autoImport._

import com.typesafe.sbt.digest.Import._
import com.typesafe.sbt.gzip.Import._
import com.typesafe.sbt.jse.JsEngineImport.JsEngineKeys
import com.typesafe.sbt.less.Import._
import com.typesafe.sbt.rjs.Import._
import com.typesafe.sbt.web.Import._
import com.typesafe.sbt.web.SbtWeb

import sbtbuildinfo.Plugin._
import com.sksamuel.scapegoat.sbt.ScapegoatSbtPlugin.autoImport._
import com.typesafe.sbt.SbtScalariform.{ ScalariformKeys, defaultScalariformSettings }
import net.virtualvoid.sbt.graph.Plugin.graphSettings

object Server {
  private[this] val dependencies = {
    import Dependencies._
    Seq(
      Database.jdub, Database.postgresJdbc,
      Play.playFilters, Play.playWs, Testing.akkaTestkit,
      Metrics.metrics, Metrics.healthChecks, Metrics.json, Metrics.jvm, Metrics.jettyServlet, Metrics.servlets, Metrics.graphite,
      WebJars.requireJs, WebJars.bootstrap
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

    // Sbt-Web
    pipelineStages := Seq(scalaJSProd, rjs, digest, gzip),
    includeFilter in (Assets, LessKeys.less) := "*.less",
    excludeFilter in (Assets, LessKeys.less) := "_*.less",
    JsEngineKeys.engineType := JsEngineKeys.EngineType.Node,

    // Build Info
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

    // Code Quality
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
    .aggregate(Shared.sharedJvm)
    .dependsOn(Shared.sharedJvm)
    .dependsOn(Utilities.rulesParser)
    .dependsOn(Utilities.rulesReset)
}
