import _root_.Dependencies.ScalaJs
import sbt.Keys._
import sbt._

import com.sksamuel.scapegoat.sbt.ScapegoatSbtPlugin.autoImport._
import net.virtualvoid.sbt.graph.Plugin.graphSettings
import com.typesafe.sbt.SbtScalariform.{ScalariformKeys, defaultScalariformSettings}

import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import playscalajs.ScalaJSPlay
import playscalajs.ScalaJSPlay.autoImport._

object Client {
  lazy val client = (project in file("client")).settings(
    scalaVersion := Shared.Versions.scala,
    persistLauncher := false,
    sourceMapsDirectories += Shared.sharedJs.base / "..",
    unmanagedSourceDirectories in Compile := Seq((scalaSource in Compile).value),
    libraryDependencies ++= Seq(
      ScalaJs.scalaJsDom,
      ScalaJs.uPickle
    ),
    scalaJSStage in Global := FastOptStage,
    scapegoatConsoleOutput := false,
    scapegoatIgnoredFiles := Seq(".*/JsonUtils.scala", ".*/JsonSerializers.scala"),
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  ).settings(graphSettings: _*).settings(defaultScalariformSettings: _*).enablePlugins(ScalaJSPlugin, ScalaJSPlay).dependsOn(Shared.sharedJs)
}
