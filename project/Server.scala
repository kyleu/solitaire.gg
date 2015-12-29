import com.sksamuel.scapegoat.sbt.ScapegoatSbtPlugin.autoImport._
import com.typesafe.sbt.GitVersioning
import com.typesafe.sbt.SbtScalariform.{ ScalariformKeys, scalariformSettings }
import com.typesafe.sbt.digest.Import._
import com.typesafe.sbt.gzip.Import._
import com.typesafe.sbt.jse.JsEngineImport.JsEngineKeys
import com.typesafe.sbt.jshint.Import.JshintKeys
import com.typesafe.sbt.less.Import._
import com.typesafe.sbt.rjs.Import._
import com.typesafe.sbt.web.Import._
import com.typesafe.sbt.web.SbtWeb

import net.virtualvoid.sbt.graph.Plugin.graphSettings
import play.routes.compiler.InjectedRoutesGenerator
import play.sbt.routes.RoutesKeys.{ routesGenerator, routesImport }
import playscalajs.PlayScalaJS.autoImport._
import sbt.Keys._
import sbt.Project.projectToRef
import sbt._
import sbtide.Keys.ideExcludedDirectories

object Server {
  private[this] val dependencies = {
    import Dependencies._
    Seq(
      Cache.ehCache, Database.postgresAsync, Mail.mailer,
      Akka.actor, Akka.log4j, /* Akka.cluster, Akka.contrib, Akka.persistence, Akka.remoting, */
      Play.playFilters, Play.playWs, Play.playTest, Authentication.silhouette,
      Metrics.metrics, Metrics.healthChecks, Metrics.json, Metrics.jvm, Metrics.ehcache, Metrics.jettyServlet, Metrics.servlets, Metrics.graphite,
      WebJars.requireJs, WebJars.bootstrap, WebJars.underscore, WebJars.d3, WebJars.nvd3,
      Utils.core, Utils.collection, Testing.testkit, Testing.gatlingCore, Testing.gatlingCharts
    )
  }

  private[this] lazy val serverSettings = Seq(
    name := Shared.projectId,

    libraryDependencies ++= dependencies,

    // Play
    routesGenerator := InjectedRoutesGenerator,
    routesImport ++= Seq("utils.web.RoutingImports._"),

    // Scala.js
    scalaJSProjects := Seq(Client.client),

    // Sbt-Web
    JsEngineKeys.engineType := JsEngineKeys.EngineType.Node,
    pipelineStages := Seq(scalaJSProd, rjs, digest, gzip),

    includeFilter in (Assets, LessKeys.less) := "*.less",
    excludeFilter in (Assets, LessKeys.less) := "_*.less",
    LessKeys.compress in Assets := true,

    JshintKeys.config := Some(new java.io.File("conf/.jshintrc")),

    // Code Quality
    scapegoatIgnoredFiles := Seq(".*/Row.scala", ".*/Routes.scala", ".*/ReverseRoutes.scala", ".*/JavaScriptReverseRoutes.scala", ".*/*.template.scala"),
    scapegoatDisabledInspections := Seq("DuplicateImport"),
    scapegoatVersion := "1.1.0",
    ScalariformKeys.preferences := ScalariformKeys.preferences.value,

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

  lazy val server = Project(
    id = Shared.projectId,
    base = file(".")
  )
    .enablePlugins(GitVersioning)
    .enablePlugins(SbtWeb)
    .enablePlugins(play.sbt.PlayScala)
    .settings(Shared.commonSettings: _*)
    .settings(serverSettings: _*)
    .settings(scalariformSettings: _*)
    .settings(graphSettings: _*)
    .aggregate(projectToRef(Client.client))
    .aggregate(Shared.sharedJvm)
    .dependsOn(Shared.sharedJvm)
    .dependsOn(Utilities.screenshotCreator)
}
