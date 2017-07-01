package utils

import java.net.InetAddress

import com.github.mauricio.async.db.{Configuration => DbConfig}
import play.api.{Environment, Mode}
import utils.metrics.MetricsConfig

object Config {
  val projectId = "solitaire-gg"
  val projectName = "Solitaire.gg"
  val projectDescription = "Solitaire.gg is a collection of card games. It's quite good."
  val version = "0.1"
  val hostname = InetAddress.getLocalHost.getHostName
}

@javax.inject.Singleton
class Config @javax.inject.Inject() (cnf: play.api.Configuration, env: Environment) {
  val debug = env.mode == Mode.Dev

  val hostname = cnf.get[Option[String]]("host").getOrElse("localhost")
  val fileCacheDir = new java.io.File(cnf.get[Option[String]]("cache.dir").getOrElse("./cache"))

  val metrics: MetricsConfig = MetricsConfig(
    jmxEnabled = cnf.get[Option[Boolean]]("metrics.jmx.enabled").getOrElse(true),
    graphiteEnabled = cnf.get[Option[Boolean]]("metrics.graphite.enabled").getOrElse(false),
    graphiteServer = cnf.get[Option[String]]("metrics.graphite.server").getOrElse("127.0.0.1"),
    graphitePort = cnf.get[Option[Int]]("metrics.graphite.port").getOrElse(2003),
    servletEnabled = cnf.get[Option[Boolean]]("metrics.servlet.enabled").getOrElse(true),
    servletPort = cnf.get[Option[Int]]("metrics.servlet.port").getOrElse(9001)
  )

  val databaseConfiguration = new DbConfig(
    host = cnf.get[Option[String]]("database.host").getOrElse("localhost"),
    port = 5432,
    database = cnf.get[Option[String]]("database.schema"),
    username = cnf.get[Option[String]]("database.username").getOrElse(Config.projectId),
    password = cnf.get[Option[String]]("database.password")
  )

  val sparkEnabled = cnf.get[Option[Boolean]]("spark.enabled").getOrElse(false)
  val sparkMaster = cnf.get[Option[String]]("spark.master").getOrElse("local[8]")
  val sparkPort = cnf.get[Option[Int]]("spark.uiport").getOrElse(4040)

  // Admin
  val adminEmail = cnf.get[Option[String]]("game.admin.email").getOrElse("admin@localhost")

  // Notifications
  val slackEnabled = cnf.get[Option[Boolean]]("slack.enabled").getOrElse(false)
  val slackUrl = cnf.get[Option[String]]("slack.url").getOrElse("no_url_provided")

  // Metrics
  val jmxEnabled = cnf.get[Option[Boolean]]("metrics.jmx.enabled").getOrElse(false)
  val graphiteEnabled = cnf.get[Option[Boolean]]("metrics.graphite.enabled").getOrElse(false)
  val graphiteServer = cnf.get[Option[String]]("metrics.graphite.server").getOrElse("localhost")
  val graphitePort = cnf.get[Option[Int]]("metrics.graphite.port").getOrElse(8080)
  val servletEnabled = cnf.get[Option[Boolean]]("metrics.servlet.enabled").getOrElse(false)
  val servletPort = cnf.get[Option[Int]]("metrics.servlet.port").getOrElse(9001)
}
