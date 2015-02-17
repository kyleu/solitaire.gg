package utils

import com.typesafe.config.ConfigFactory
import play.api.Play

object Config {
  private val cnf = ConfigFactory.load()

  val projectId = "scalataire"
  val projectName = "Scalataire"
  val version = "0.1"

  val debug = !Play.isProd(Play.current)

  // Metrics
  val jmxEnabled = cnf.getBoolean("metrics.jmx.enabled")
  val graphiteEnabled = cnf.getBoolean("metrics.graphite.enabled")
  val graphiteServer = cnf.getString("metrics.graphite.server")
  val graphitePort = cnf.getInt("metrics.graphite.port")
  val servletEnabled = cnf.getBoolean("metrics.servlet.enabled")
  val servletPort = cnf.getInt("metrics.servlet.port")
}
