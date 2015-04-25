import sbt._
import sbt.Keys._

import com.sksamuel.scapegoat.sbt.ScapegoatSbtPlugin.autoImport._
import net.virtualvoid.sbt.graph.Plugin.graphSettings
import com.typesafe.sbt.SbtScalariform.{ScalariformKeys, defaultScalariformSettings}

object Utilities {
  lazy val rulesParser = (project in file("util/rulesParser")).settings(
    name := "rules-parser",
    scalaVersion := Shared.Versions.scala,
    libraryDependencies ++= Seq(Dependencies.Play.playJson),
    scapegoatConsoleOutput := false,
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  ).settings(graphSettings: _*).settings(defaultScalariformSettings: _*).dependsOn(Shared.sharedJvm)

  lazy val rulesReset = (project in file("util/rulesReset")).settings(
    name := "rules-reset",
    scalaVersion := Shared.Versions.scala,
    scapegoatConsoleOutput := false,
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  ).settings(graphSettings: _*).settings(defaultScalariformSettings: _*)
}
