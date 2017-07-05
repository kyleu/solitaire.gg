import com.sksamuel.scapegoat.sbt.ScapegoatSbtPlugin.autoImport._
import sbt._
import sbt.Keys._

import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import webscalajs.ScalaJSWeb

import net.virtualvoid.sbt.graph.DependencyGraphSettings.graphSettings
import com.typesafe.sbt.SbtScalariform.{ScalariformKeys, scalariformSettings}

import Dependencies.Serialization._

object Shared {
  val projectId = "solitaire-gg"
  val projectName = "Solitaire.gg"

  val compileOptions = Seq(
    "target:jvm-1.8", "-encoding", "UTF-8", "-feature", "-deprecation", "-explaintypes", "-feature", "-unchecked",
    "–Xcheck-null", "-Xfatal-warnings", /* "-Xlint", */ "-Xcheckinit", "-Xfuture",
    "-Yno-adapted-args", "-Ywarn-dead-code", "-Ywarn-inaccessible", "-Ywarn-nullary-override", "-Ywarn-numeric-widen", "-Ywarn-infer-any"
  )

  object Versions {
    val app = "0.1-SNAPSHOT"
    val scala = "2.12.2"
  }

  val commonSettings = Seq(
    version := Shared.Versions.app,
    scalaVersion := Versions.scala,

    scalacOptions ++= Shared.compileOptions,
    scalacOptions in (Compile, console) ~= (_.filterNot(Set(
      "-Ywarn-unused:imports",
      "-Xfatal-warnings"
    ))),
    scalacOptions in Test ++= Seq("-Yrangepos"),

    // Prevent Scaladoc
    publishArtifact in (Compile, packageDoc) := false,
    publishArtifact in packageDoc := false,
    sources in (Compile,doc) := Seq.empty,

    // Resolvers
    resolvers += Resolver.jcenterRepo,
    resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/",
    resolvers += "DefinitelyScala" at "http://dl.bintray.com/definitelyscala/maven",

    // Code Quality
    scapegoatVersion := Dependencies.Utils.scapegoatVersion,
    ScalariformKeys.preferences := ScalariformKeys.preferences.value,

    testFrameworks += new TestFramework("utest.runner.Framework")
  ) ++ graphSettings ++ scalariformSettings

  lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared")).settings(commonSettings: _*).settings(libraryDependencies ++= Seq(
    "io.circe" %%% "circe-core" % circeVersion,
    "io.circe" %%% "circe-generic" % circeVersion,
    "io.circe" %%% "circe-generic-extras" % circeVersion,
    "io.circe" %%% "circe-parser" % circeVersion,
    "com.beachape" %%% "enumeratum-circe" % Dependencies.Utils.enumeratumVersion,
    "com.lihaoyi" %%% "utest" % Dependencies.Testing.uTestVersion % "test"
  ))

  lazy val sharedJs = shared.js.enablePlugins(ScalaJSWeb).settings(scalaJSStage in Global := FastOptStage)

  lazy val sharedJvm = shared.jvm
}
