import com.sksamuel.scapegoat.sbt.ScapegoatSbtPlugin.autoImport._
import com.typesafe.sbt.{ GitBranchPrompt, GitVersioning }
import sbt._
import sbt.Keys._

import net.virtualvoid.sbt.graph.DependencyGraphSettings.graphSettings
import com.typesafe.sbt.SbtScalariform.{ScalariformKeys, scalariformSettings}

import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

import playscalajs.ScalaJSPlay
import playscalajs.ScalaJSPlay.autoImport._

object Shared {
  val projectId = "solitaire-gg"

  val compileOptions = Seq(
    "-encoding", "UTF-8", "-feature", "-deprecation", "-unchecked", "–Xcheck-null", "-Xfatal-warnings", "-Xlint",
    "-Ywarn-adapted-args", "-Ywarn-dead-code", "-Ywarn-inaccessible", "-Ywarn-nullary-override", "-Ywarn-numeric-widen"
  )

  object Versions {
    val app = "0.1-SNAPSHOT"
    val scala = "2.11.8"
  }

  val commonSettings = Seq(
    version := Shared.Versions.app,
    scalaVersion := Versions.scala,

    scalacOptions ++= Shared.compileOptions,
    scalacOptions in Test ++= Seq("-Yrangepos"),

    // Prevent Scaladoc
    doc in Compile <<= target.map(_ / "none"),
    sources in (Compile, doc) := Seq.empty,
    publishArtifact in (Compile, packageDoc) := false,

    // Resolvers
    resolvers += Resolver.jcenterRepo
  )

  lazy val sharedJs = (crossProject.crossType(CrossType.Pure) in file("shared"))
    .enablePlugins(ScalaJSPlay)
    .settings(commonSettings: _*)
    .settings(
      scalaJSStage in Global := FastOptStage,
      scapegoatIgnoredFiles := Seq(".*"),
      scapegoatVersion := "1.2.1"
    )
    .js

  lazy val sharedJvm = (project in file("shared")).settings(
    scapegoatVersion := "1.2.1",
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  )
    .enablePlugins(GitVersioning)
    .enablePlugins(GitBranchPrompt)
    .settings(commonSettings: _*)
    .settings(graphSettings: _*)
    .settings(scalariformSettings: _*)
}
