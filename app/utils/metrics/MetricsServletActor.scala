package utils.metrics

import java.net.InetSocketAddress
import java.util.concurrent.TimeUnit

import com.codahale.metrics.graphite.{ Graphite, GraphiteReporter }
import com.codahale.metrics.{ MetricFilter, JmxReporter }
import com.codahale.metrics.servlets.AdminServlet
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.nio.SelectChannelConnector
import org.eclipse.jetty.servlet.ServletContextHandler
import utils.{ Logging, Config }

class MetricsServletActor extends InstrumentedActor with Logging {
  private var jmxReporter: Option[JmxReporter] = None
  private var graphiteReporter: Option[GraphiteReporter] = None
  private var httpServer: Option[Server] = None

  override def preStart() {
    if (Config.jmxEnabled) {
      log.info("Reporting metrics over JMX.")
      jmxReporter = Some(JmxReporter.forRegistry(Instrumented.metricRegistry).build())
      jmxReporter.get.start()
    }

    if (Config.graphiteEnabled) {
      log.info("Starting Graphite reporter for [" + Config.graphiteServer + ":" + Config.graphitePort + "].")
      val graphiteServer = new Graphite(new InetSocketAddress(Config.graphiteServer, Config.graphitePort))
      graphiteReporter = Some(
        GraphiteReporter.forRegistry(Instrumented.metricRegistry)
          .prefixedWith("web1.example.com")
          .convertRatesTo(TimeUnit.SECONDS)
          .convertDurationsTo(TimeUnit.MILLISECONDS)
          .filter(MetricFilter.ALL)
          .build(graphiteServer)
      )
      graphiteReporter.get.start(1, TimeUnit.MINUTES)
    }

    if (Config.servletEnabled) {
      log.info("Starting metrics servlet at [http://0.0.0.0:" + Config.servletPort + "/].")
      httpServer = Some(createJettyServer())
      httpServer.get.start()
    }

    super.preStart()
  }

  override def postStop() {
    jmxReporter.map { r =>
      r.stop()
      r.close()
    }
    jmxReporter = None

    graphiteReporter.map { r =>
      r.stop()
      r.close()
    }
    graphiteReporter = None

    httpServer.map { s =>
      s.stop()
      s.join()
    }
    httpServer = None

    super.postStop()
  }

  override def receiveRequest = {
    case _ => // Ignore...
  }

  def createJettyServer(): Server = {
    val server = new Server()

    val connector = new SelectChannelConnector()
    connector.setHost("0.0.0.0")
    connector.setPort(Config.servletPort)
    server.addConnector(connector)

    val handler = new ServletContextHandler()
    handler.getServletContext.setAttribute("com.codahale.metrics.servlets.MetricsServlet.registry", Instrumented.metricRegistry)
    handler.getServletContext.setAttribute("com.codahale.metrics.servlets.HealthCheckServlet.registry", Checked.healthCheckRegistry)
    handler.setContextPath("/")
    handler.addServlet(classOf[AdminServlet], "/*")
    server.setHandler(handler)

    server
  }
}
