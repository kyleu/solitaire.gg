import com.typesafe.sbt.{ GitBranchPrompt, GitVersioning }
import sbt._
import sbt.Keys._

import net.virtualvoid.sbt.graph.Plugin.graphSettings
import com.typesafe.sbt.SbtScalariform.{ScalariformKeys, defaultScalariformSettings}

object Utilities {
  lazy val iconCreator = (project in file("util/iconCreator")).settings(
    name := "icon-creator",
    scalaVersion := Shared.Versions.scala,
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  )
    .enablePlugins(GitVersioning)
    .enablePlugins(GitBranchPrompt)
    .settings(graphSettings: _*)
    .settings(defaultScalariformSettings: _*)

  lazy val rulesParser = (project in file("util/rulesParser")).settings(
    name := "rules-parser",
    scalaVersion := Shared.Versions.scala,
    libraryDependencies ++= Seq(Dependencies.Play.playJson),
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  )
    .enablePlugins(GitVersioning)
    .enablePlugins(GitBranchPrompt)
    .settings(graphSettings: _*)
    .settings(defaultScalariformSettings: _*)
    .dependsOn(Shared.sharedJvm)

  lazy val rulesReset = (project in file("util/rulesReset")).settings(
    name := "rules-reset",
    scalaVersion := Shared.Versions.scala,
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  )
    .enablePlugins(GitVersioning)
    .enablePlugins(GitBranchPrompt)
    .settings(graphSettings: _*)
    .settings(defaultScalariformSettings: _*)
}
