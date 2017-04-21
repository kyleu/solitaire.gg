import com.sksamuel.scapegoat.sbt.ScapegoatSbtPlugin.autoImport._
import com.typesafe.sbt.{ GitBranchPrompt, GitVersioning }
import sbt._
import sbt.Keys._

import net.virtualvoid.sbt.graph.DependencyGraphSettings.graphSettings
import com.typesafe.sbt.SbtScalariform.{ScalariformKeys, scalariformSettings}

import sbtcrossproject.CrossPlugin.autoImport._
import scalajscrossproject.ScalaJSCrossPlugin.autoImport._
import scala.scalanative.sbtplugin.NativePlatform
import scala.scalanative.sbtplugin.ScalaNativePlugin.autoImport._

object Shared {
  val projectId = "solitaire-gg"
  val projectName = "Solitaire.gg"

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
    publishArtifact in (Compile, packageDoc) := false,
    publishArtifact in packageDoc := false,
    sources in (Compile,doc) := Seq.empty,

    // Resolvers
    resolvers += Resolver.jcenterRepo,

    // Code Quality
    scapegoatVersion := Dependencies.Utils.scapegoatVersion,
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  ) ++ graphSettings ++ scalariformSettings


  lazy val shared = (crossProject(JSPlatform, JVMPlatform, NativePlatform).crossType(CrossType.Pure) in file("shared")).settings(commonSettings: _*)

  lazy val sharedJs = shared.js

  lazy val sharedJvm = shared.jvm

  lazy val sharedNative = shared.native
}
