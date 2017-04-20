import com.sksamuel.scapegoat.sbt.ScapegoatSbtPlugin.autoImport._
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import playscalajs.ScalaJSPlay
import sbt.Keys._
import sbt._

object Client {
  private[this] val clientSettings = Seq(
    unmanagedSourceDirectories in Compile := Seq((scalaSource in Compile).value),
    libraryDependencies ++= Seq(
      "com.definitelyscala" %%% "scala-js-phaser" % Dependencies.ScalaJS.definitelyScalaVersion,
      "com.definitelyscala" %%% "scala-js-jquery" % Dependencies.ScalaJS.definitelyScalaVersion,
      "com.definitelyscala" %%% "scala-js-materializecss" % Dependencies.ScalaJS.definitelyScalaVersion,
      "org.scala-js" %%% "scalajs-dom" % Dependencies.ScalaJS.domVersion
    ),
    scalaJSStage in Global := FastOptStage,
    scapegoatIgnoredFiles := Seq(
      ".*/AnalyticsHelper.scala",
      ".*/GameSerializers.scala",
      ".*/RequestMessageSerializers.scala",
      ".*/ResponseMessageSerializers.scala",
      ".*/JsonUtils.scala",
      ".*/JsonSerializers.scala"
    )
  )

  lazy val client = (project in file("client"))
    .enablePlugins(ScalaJSPlugin, ScalaJSPlay)
    .settings(Shared.commonSettings: _*)
    .settings(clientSettings: _*)
    .dependsOn(Shared.sharedJs)
}
