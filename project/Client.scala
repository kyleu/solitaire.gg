import com.sksamuel.scapegoat.sbt.ScapegoatSbtPlugin.autoImport._
import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import playscalajs.ScalaJSPlay
import sbt.Keys._
import sbt._

object Client {
  private[this] val clientSettings = Shared.commonSettings ++ Seq(
    unmanagedSourceDirectories in Compile := Seq((scalaSource in Compile).value),
    libraryDependencies ++= Seq(
      "com.definitelyscala" %%% "scala-js-phaser" % Dependencies.ScalaJS.definitelyScalaVersion,
      "be.doeraene" %%% "scalajs-jquery" % Dependencies.ScalaJS.jQueryVersion,
      "com.lihaoyi" %%% "scalatags" % Dependencies.ScalaJS.scalaTagsVersion,
      "org.scala-js" %%% "scalajs-dom" % Dependencies.ScalaJS.domVersion,
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
    scapegoatVersion := Dependencies.Utils.scapegoatVersion,
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  )

  lazy val client = (project in file("client")).enablePlugins(ScalaJSPlugin, ScalaJSPlay).settings(clientSettings: _*).dependsOn(Shared.sharedJs)
}
