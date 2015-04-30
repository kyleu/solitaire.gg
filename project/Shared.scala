import sbt._
import sbt.Keys._

import net.virtualvoid.sbt.graph.Plugin.graphSettings
import com.typesafe.sbt.SbtScalariform.{ScalariformKeys, defaultScalariformSettings}

import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

import playscalajs.ScalaJSPlay
import playscalajs.ScalaJSPlay.autoImport._

object Shared {
  val projectId = "scalataire"

  val compileOptions = Seq("-encoding", "UTF-8", "-feature", "-deprecation", "-unchecked", "–Xcheck-null", "-Xfatal-warnings", "-Xlint")

  object Versions {
    val app = "0.1-SNAPSHOT"
    val scala = "2.11.6"
  }

  lazy val sharedJs = (crossProject.crossType(CrossType.Pure) in file("shared")).settings(
    scalaVersion := Versions.scala
  ).enablePlugins(ScalaJSPlay).settings(sourceMapsBase := baseDirectory.value / "..", scalaJSStage in Global := FastOptStage).js

  lazy val sharedJvm = (project in file("shared")).settings(
    scalaVersion := Versions.scala,
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  ).settings(graphSettings: _*).settings(defaultScalariformSettings: _*)
}
