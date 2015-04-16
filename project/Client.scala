import sbt.Keys._
import sbt._

import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import playscalajs.ScalaJSPlay
import playscalajs.ScalaJSPlay.autoImport._

object Client {
  lazy val client = (project in file("client")).settings(
    scalaVersion := Shared.Versions.scala,
    persistLauncher := true,
    persistLauncher in Test := false,
    sourceMapsDirectories += Shared.sharedJs.base / "..",
    unmanagedSourceDirectories in Compile := Seq((scalaSource in Compile).value),
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.8.0",
      "com.lihaoyi" %%% "upickle" % "0.2.8"
    )
  ).enablePlugins(ScalaJSPlugin, ScalaJSPlay).dependsOn(Shared.sharedJs)
}
