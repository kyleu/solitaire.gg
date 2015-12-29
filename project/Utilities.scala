import com.typesafe.sbt.{ GitBranchPrompt, GitVersioning }
import io.gatling.sbt.GatlingPlugin
import sbt._
import sbt.Keys._

import net.virtualvoid.sbt.graph.Plugin.graphSettings
import com.typesafe.sbt.SbtScalariform.{ScalariformKeys, scalariformSettings}
import pl.project13.scala.sbt.JmhPlugin

object Utilities {
  lazy val benchmarking = (project in file("util/benchmarking")).settings(
    name := "benchmarking",
    libraryDependencies ++= Seq(
      Dependencies.Testing.gatlingCore,
      Dependencies.Testing.gatlingCharts
    ),
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  )
    .dependsOn(Shared.sharedJvm)
    .dependsOn(Server.server)
    .enablePlugins(GitVersioning)
    .enablePlugins(GitBranchPrompt)
    .enablePlugins(GatlingPlugin)
    .enablePlugins(JmhPlugin)
    .settings(Shared.commonSettings: _*)
    .settings(graphSettings: _*)
    .settings(scalariformSettings: _*)

  lazy val iconCreator = (project in file("util/iconCreator")).settings(
    name := "icon-creator",
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  )
    .enablePlugins(GitVersioning)
    .enablePlugins(GitBranchPrompt)
    .settings(Shared.commonSettings: _*)
    .settings(graphSettings: _*)
    .settings(scalariformSettings: _*)

  lazy val screenshotCreator = (project in file("util/screenshotCreator")).settings(
    name := "screenshot-creator",
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  )
    .enablePlugins(GitVersioning)
    .enablePlugins(GitBranchPrompt)
    .settings(Shared.commonSettings: _*)
    .settings(graphSettings: _*)
    .settings(scalariformSettings: _*)
    .dependsOn(Shared.sharedJvm)
}
