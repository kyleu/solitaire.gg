package utils.metrics

import com.codahale.metrics.health.HealthCheckRegistry
import com.codahale.metrics.health.jvm.ThreadDeadlockHealthCheck
import nl.grons.metrics.scala.CheckedBuilder

object Checked {
  val healthCheckRegistry = new HealthCheckRegistry()
  healthCheckRegistry.register("jvm.deadlocks", new ThreadDeadlockHealthCheck())
}

trait Checked extends CheckedBuilder {
  override val registry = Checked.healthCheckRegistry
}
