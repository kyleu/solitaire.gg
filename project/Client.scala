import com.sksamuel.scapegoat.sbt.ScapegoatSbtPlugin.autoImport._
import com.typesafe.sbt.{ GitBranchPrompt, GitVersioning }
import com.typesafe.sbt.SbtScalariform.{ ScalariformKeys, scalariformSettings }
import net.virtualvoid.sbt.graph.DependencyGraphSettings.graphSettings
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import playscalajs.ScalaJSPlay
import playscalajs.ScalaJSPlay.autoImport._
import sbt.Keys._
import sbt._

object Client {
  private[this] val clientSettings = Seq(
    persistLauncher := false,
    unmanagedSourceDirectories in Compile := Seq((scalaSource in Compile).value),
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.1",
      "com.lihaoyi" %%% "upickle" % "0.4.4"
    ),
    scalaJSStage in Global := FastOptStage,
    scapegoatIgnoredFiles := Seq(
      ".*/AnalyticsHelper.scala",
      ".*/GameSerializers.scala",
      ".*/RequestMessageSerializers.scala",
      ".*/ResponseMessageSerializers.scala",
      ".*/JsonUtils.scala",
      ".*/JsonSerializers.scala"
    ),
    scapegoatVersion := "1.2.1",
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  )

  lazy val client = (project in file("client"))
    .enablePlugins(GitVersioning)
    .enablePlugins(GitBranchPrompt)
    .enablePlugins(ScalaJSPlugin, ScalaJSPlay)
    .settings(Shared.commonSettings: _*)
    .settings(graphSettings: _*)
    .settings(scalariformSettings: _*)
    .settings(clientSettings: _*)
    .dependsOn(Shared.sharedJs)
}
