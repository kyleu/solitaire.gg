import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import io.gatling.sbt.GatlingPlugin
import pl.project13.scala.sbt.JmhPlugin
import sbt.Keys._
import sbt._

import scala.scalanative.sbtplugin.ScalaNativePlugin

object Utilities {
  lazy val benchmarking = (project in file("util/benchmarking")).settings(
    name := "benchmarking",
    libraryDependencies ++= Seq(
      Dependencies.Testing.gatlingCore,
      Dependencies.Testing.gatlingCharts
    ),
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  )
    .settings(Shared.commonSettings: _*)
    .dependsOn(Shared.sharedJvm, Server.server)
    .enablePlugins(GatlingPlugin, JmhPlugin)

  lazy val commandLine = (project in file("util/commandLine")).settings(
    name := "command-line",
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  ).settings(Shared.commonSettings: _*).enablePlugins(ScalaNativePlugin).dependsOn(Shared.sharedNative)

  lazy val iconCreator = (project in file("util/iconCreator")).settings(
    name := "icon-creator",
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  ).settings(Shared.commonSettings: _*)

  lazy val screenshotCreator = (project in file("util/screenshotCreator")).settings(
    name := "screenshot-creator",
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  ).settings(Shared.commonSettings: _*).dependsOn(Shared.sharedJvm)
}
