package utils

import com.github.mauricio.async.db.{ Configuration => DbConfig }
import play.api.Play

object Config {
  val projectId = "solitaire-gg"
  val projectName = "Solitaire.gg"
  val projectDescription = "Solitaire.gg is a collection of card games. It's quite good."
  val version = "0.1"
}

@javax.inject.Singleton
class Config @javax.inject.Inject() (cnf: play.api.Configuration) {
  lazy val debug = !Play.isProd(Play.current)

  val hostname = cnf.getString("host").getOrElse("localhost")
  val fileCacheDir = cnf.getString("cache.dir")

  // Database
  val databaseConfiguration = new DbConfig(
    host = cnf.getString("database.host").getOrElse("localhost"),
    port = 5432,
    database = cnf.getString("database.schema"),
    username = cnf.getString("database.username").getOrElse(Config.projectId),
    password = cnf.getString("database.password")
  )

  // Spark
  val sparkEnabled = cnf.getBoolean("spark.enabled").getOrElse(false)
  val sparkMaster = cnf.getString("spark.master").getOrElse("local[8]")
  val sparkPort = cnf.getInt("spark.uiport").getOrElse(4040)

  // Admin
  val adminEmail = cnf.getString("game.admin.email").getOrElse("admin@localhost")

  // Notifications
  val slackEnabled = cnf.getBoolean("slack.enabled").getOrElse(false)
  val slackUrl = cnf.getString("slack.url").getOrElse("no_url_provided")

  // Metrics
  val jmxEnabled = cnf.getBoolean("metrics.jmx.enabled").getOrElse(false)
  val graphiteEnabled = cnf.getBoolean("metrics.graphite.enabled").getOrElse(false)
  val graphiteServer = cnf.getString("metrics.graphite.server").getOrElse("localhost")
  val graphitePort = cnf.getInt("metrics.graphite.port").getOrElse(8080)
  val servletEnabled = cnf.getBoolean("metrics.servlet.enabled").getOrElse(false)
  val servletPort = cnf.getInt("metrics.servlet.port").getOrElse(9001)
}
