import com.sksamuel.scapegoat.sbt.ScapegoatSbtPlugin.autoImport._
import com.typesafe.sbt.GitVersioning
import com.typesafe.sbt.digest.Import._
import com.typesafe.sbt.gzip.Import._
import com.typesafe.sbt.jse.JsEngineImport.JsEngineKeys
import com.typesafe.sbt.less.Import._
import com.typesafe.sbt.web.Import._
import com.typesafe.sbt.web.SbtWeb
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import com.typesafe.sbt.packager.debian.DebianPlugin
import com.typesafe.sbt.packager.docker.DockerPlugin
import com.typesafe.sbt.packager.jdkpackager.JDKPackagerPlugin
import com.typesafe.sbt.packager.linux.LinuxPlugin
import com.typesafe.sbt.packager.rpm.RpmPlugin
import com.typesafe.sbt.packager.universal.UniversalPlugin
import com.typesafe.sbt.packager.windows.WindowsPlugin
import play.routes.compiler.InjectedRoutesGenerator
import play.sbt.PlayImport.PlayKeys.devSettings
import play.sbt.routes.RoutesKeys.{routesGenerator, routesImport}
import webscalajs.WebScalaJS.autoImport._
import sbt.Keys._
import sbt.Project.projectToRef
import sbt._
import sbtide.Keys.ideExcludedDirectories

object Server {
  private[this] val dependencies = {
    import Dependencies._
    Seq(
      Metrics.metrics, Metrics.metricsJvm, Metrics.metricsHttp, Metrics.metricsPush,
      Database.hikariCp, Database.postgres, GraphQL.sangria, GraphQL.playJson, GraphQL.circe,
      Akka.actor, Akka.log4j, Akka.testkit, /* Akka.cluster, Akka.contrib, Akka.persistence, Akka.remoting, */
      Play.filters, Play.ws, Play.test, Play.mailer, Play.mailerGuice,
      WebJars.requireJs, WebJars.bootstrap, WebJars.underscore, WebJars.d3, WebJars.nvd3, WebJars.materialize, WebJars.jquery,
      Utils.betterFiles, Utils.slf4j, Utils.xmlBind, Testing.gatlingCore, Testing.gatlingCharts, Play.test
    )
  }

  private[this] lazy val serverSettings = Seq(
    name := Shared.projectId,

    libraryDependencies ++= dependencies,

    // Play
    routesGenerator := InjectedRoutesGenerator,
    routesImport ++= Seq("util.web.QueryStringUtils._"),
    devSettings := Seq("play.server.akka.requestTimeout" -> "infinite"),

    // Scala.js
    scalaJSProjects := Seq(Client.client),

    // Sbt-Web
    JsEngineKeys.engineType := JsEngineKeys.EngineType.Node,

    pipelineStages in Assets := Seq(scalaJSPipeline),
    pipelineStages := Seq(digest, gzip),

    includeFilter in (Assets, LessKeys.less) := "*.less",
    excludeFilter in (Assets, LessKeys.less) := "_*.less",
    LessKeys.compress in Assets := true,

    // Code Quality
    scapegoatIgnoredFiles := Seq(
      ".*/Row.scala", ".*/Routes.scala", ".*/ReverseRoutes.scala", ".*/JavaScriptReverseRoutes.scala", ".*/*.template.scala",
      ".*/SeedSerializers.scala"
    ),
    scapegoatDisabledInspections := Seq("DuplicateImport", "FinalModifierOnCaseClass"),

    // SBT Output
    ivyLoggingLevel := UpdateLogging.Quiet,
    parallelExecution in Test := true,

    // IDE Settings
    ideExcludedDirectories := Seq(
      new File("offline/bin"), new File("offline/build"), new File("offline/assets"),
      new File("public/lib"),
      new File("target/streams"), new File("target/web")
    )
  )

  lazy val server = Project(id = Shared.projectId, base = file("."))
    .enablePlugins(
      GitVersioning, SbtWeb, play.sbt.PlayScala, JavaAppPackaging,
      UniversalPlugin, LinuxPlugin, DebianPlugin, RpmPlugin, DockerPlugin, WindowsPlugin, JDKPackagerPlugin
    )
    .settings(Shared.commonSettings ++ serverSettings: _*)
    .dependsOn(Shared.sharedJvm, Utilities.screenshotCreator)
}
