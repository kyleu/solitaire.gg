package utils

import com.typesafe.config.ConfigFactory
import play.api.Play

object Config {
  private[this] val cnf = ConfigFactory.load()

  val projectId = utils.BuildInfo.name
  val projectName = "Scalataire"
  val version = "0.1"
  val hostname = cnf.getString("play.host")

  val debug = !Play.isProd(Play.current)

  val defaultCardSet = cnf.getString("game.default.cardSet")
  val defaultCardSize = cnf.getString("game.default.cardSize")

  // Metrics
  val jmxEnabled = cnf.getBoolean("metrics.jmx.enabled")
  val graphiteEnabled = cnf.getBoolean("metrics.graphite.enabled")
  val graphiteServer = cnf.getString("metrics.graphite.server")
  val graphitePort = cnf.getInt("metrics.graphite.port")
  val servletEnabled = cnf.getBoolean("metrics.servlet.enabled")
  val servletPort = cnf.getInt("metrics.servlet.port")
}
