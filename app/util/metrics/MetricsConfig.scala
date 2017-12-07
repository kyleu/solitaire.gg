package util.metrics

import play.api.Configuration

@javax.inject.Singleton
class MetricsConfig @javax.inject.Inject() (cnf: Configuration) {
  val prometheusEnabled = cnf.get[Boolean]("metrics.prometheus.enabled")
  val prometheusPort = cnf.get[Int]("metrics.prometheus.port")
}

