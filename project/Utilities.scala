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

  lazy val screenshotCreator = (project in file("util/screenshotCreator")).settings(
    name := "screenshot-creator",
    scalaVersion := Shared.Versions.scala,
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  )
    .enablePlugins(GitVersioning)
    .enablePlugins(GitBranchPrompt)
    .settings(graphSettings: _*)
    .settings(defaultScalariformSettings: _*)
    .dependsOn(Shared.sharedJvm)
}
