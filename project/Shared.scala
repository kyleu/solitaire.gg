import com.sksamuel.scapegoat.sbt.ScapegoatSbtPlugin.autoImport._
import sbt._
import sbt.Keys._

import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import playscalajs.ScalaJSPlay

import net.virtualvoid.sbt.graph.DependencyGraphSettings.graphSettings
import com.typesafe.sbt.SbtScalariform.{ScalariformKeys, scalariformSettings}


object Shared {
  val projectId = "solitaire-gg"
  val projectName = "Solitaire.gg"

  val compileOptions = Seq(
    "-encoding", "UTF-8", "-feature", "-deprecation", "-unchecked", "–Xcheck-null", "-Xfatal-warnings", "-Xlint",
    "-Ywarn-adapted-args", "-Ywarn-dead-code", "-Ywarn-inaccessible", "-Ywarn-nullary-override", "-Ywarn-numeric-widen"
  )

  object Versions {
    val app = "0.1-SNAPSHOT"
    val scala = "2.11.11"
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

  lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared")).settings(commonSettings: _*).jsSettings(libraryDependencies ++= Seq(
    "io.circe" %%% "circe-core" % Dependencies.Serialization.circeVersion,
    "io.circe" %%% "circe-generic" % Dependencies.Serialization.circeVersion,
    "io.circe" %%% "circe-parser" % Dependencies.Serialization.circeVersion,
    "com.beachape" %%% "enumeratum-circe" % Dependencies.Utils.enumeratumVersion
  )).jvmSettings(libraryDependencies ++= Seq(
    Dependencies.Serialization.circeCore, Dependencies.Serialization.circeGeneric, Dependencies.Serialization.circeParser, Dependencies.Utils.enumeratumCirce
  ))

  lazy val sharedJs = shared.js.enablePlugins(ScalaJSPlay).settings(scalaJSStage in Global := FastOptStage)

  lazy val sharedJvm = shared.jvm
}
