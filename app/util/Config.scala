package util

import java.net.InetAddress

import play.api.{Environment, Mode}
import util.metrics.MetricsConfig

object Config {
  val projectId = "solitaire_gg"
  val projectName = "Solitaire.gg"
  val projectDescription = "Solitaire.gg is a collection of card games. It's quite good."
  val version = "0.1"
  val hostname = InetAddress.getLocalHost.getHostName
}

@javax.inject.Singleton
class Config @javax.inject.Inject() (cnf: play.api.Configuration, env: Environment, val metrics: MetricsConfig) {
  val debug = env.mode == Mode.Dev

  def underlying() = cnf

  val hostname = cnf.get[Option[String]]("host").getOrElse("localhost")
  val fileCacheDir = new java.io.File(cnf.get[Option[String]]("cache.dir").getOrElse("./cache"))

  val sparkEnabled = cnf.get[Option[Boolean]]("spark.enabled").getOrElse(false)
  val sparkMaster = cnf.get[Option[String]]("spark.master").getOrElse("local[8]")
  val sparkPort = cnf.get[Option[Int]]("spark.uiport").getOrElse(4040)

  // Admin
  val adminKey = cnf.get[Option[String]]("game.admin.key").getOrElse("admin@localhost")
  val adminEmail = cnf.get[Option[String]]("game.admin.email").getOrElse("admin@localhost")

  // Notifications
  val slackEnabled = cnf.get[Option[Boolean]]("slack.enabled").getOrElse(false)
  val slackUrl = cnf.get[Option[String]]("slack.url").getOrElse("no_url_provided")
}
