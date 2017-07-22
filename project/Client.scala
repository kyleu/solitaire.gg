import com.sksamuel.scapegoat.sbt.ScapegoatSbtPlugin.autoImport._
import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import webscalajs.ScalaJSWeb
import sbt.Keys._
import sbt._

object Client {
  private[this] val clientSettings = Shared.commonSettings ++ Seq(
    unmanagedSourceDirectories in Compile := Seq((scalaSource in Compile).value),
    libraryDependencies ++= Seq(
      "com.definitelyscala" %%% "scala-js-phaser" % Dependencies.ScalaJS.definitelyScalaVersion,
      "be.doeraene" %%% "scalajs-jquery" % Dependencies.ScalaJS.jQueryVersion,
      "com.lihaoyi" %%% "scalatags" % Dependencies.ScalaJS.scalaTagsVersion,
      "org.scala-js" %%% "scalajs-dom" % Dependencies.ScalaJS.domVersion
    ),
    scalaJSStage in Global := FastOptStage,
    scapegoatIgnoredFiles := Seq(".*/JsUtils.scala", ".*/JsonSerializers.scala", ".*/Messages.scala"),
    scapegoatVersion := Dependencies.Utils.scapegoatVersion,
    scapegoatDisabledInspections := Seq("FinalModifierOnCaseClass"),
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
  )

  lazy val client = (project in file("client")).enablePlugins(ScalaJSPlugin, ScalaJSWeb).settings(clientSettings: _*).dependsOn(Shared.sharedJs)
}
