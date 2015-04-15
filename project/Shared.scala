import sbt._
import sbt.Keys._

import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

import playscalajs.ScalaJSPlay
import playscalajs.ScalaJSPlay.autoImport._

object Shared {
  val projectId = "scalataire"

  val compileOptions = Seq(
    "-encoding", "UTF-8",
    "-feature", "-deprecation", "-unchecked",
    "–Xcheck-null", "-Xfatal-warnings", "-Xlint"
  )

  object Versions {
    val app = "0.1-SNAPSHOT"
    val scala = "2.11.6"
  }

  lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared")).
    settings(scalaVersion := Versions.scala).
    jsConfigure(_ enablePlugins ScalaJSPlay).
    jsSettings(sourceMapsBase := baseDirectory.value / "..")

  lazy val sharedJvm = shared.jvm

  lazy val sharedJs = shared.js
}
