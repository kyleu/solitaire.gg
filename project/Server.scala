import com.sksamuel.scapegoat.sbt.ScapegoatSbtPlugin.autoImport._
import com.typesafe.sbt.GitVersioning
import com.typesafe.sbt.SbtScalariform.{ ScalariformKeys, defaultScalariformSettings }
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
      Cache.ehCache, Database.postgresAsync, Mail.mailer, Authentication.silhouette,
      Play.playFilters, Play.playWs, Play.playTest,
      Metrics.metrics, Metrics.healthChecks, Metrics.json, Metrics.jvm, Metrics.ehcache, Metrics.jettyServlet, Metrics.servlets, Metrics.graphite,
      WebJars.requireJs, WebJars.bootstrap, WebJars.underscore, WebJars.d3, WebJars.nvd3,
      Akka.actor, Akka.cluster, Akka.contrib, Akka.persistence, Akka.remoting, Akka.testkit,
      Utils.core, Utils.collection
    )
  }

  private[this] lazy val serverSettings = Seq(
    name := Shared.projectId,
    version := Shared.Versions.app,
    scalaVersion := Shared.Versions.scala,

    scalacOptions ++= Shared.compileOptions,
    scalacOptions in Test ++= Seq("-Yrangepos"),

    resolvers += Resolver.jcenterRepo,
    libraryDependencies ++= dependencies,

    // Play
    routesGenerator := InjectedRoutesGenerator,
    routesImport ++= Seq("utils.web.RoutingImports._"),

    // Scala.js
    scalaJSProjects := Seq(Client.client),

    // Prevent Scaladoc
    doc in Compile <<= target.map(_ / "none"),
    sources in (Compile, doc) := Seq.empty,
    publishArtifact in (Compile, packageDoc) := false,

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
  ) ++ graphSettings ++ defaultScalariformSettings

  lazy val server = Project(
    id = Shared.projectId,
    base = file(".")
  )
    .enablePlugins(SbtWeb)
    .enablePlugins(play.sbt.PlayScala)
    .enablePlugins(GitVersioning)
    .settings(serverSettings: _*)
    .aggregate(projectToRef(Client.client))
    .aggregate(Shared.sharedJvm)
    .dependsOn(Shared.sharedJvm)
    .dependsOn(Utilities.screenshotCreator)
}
