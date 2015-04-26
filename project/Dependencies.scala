import sbt._

import play.Play.autoImport._

object Dependencies {
  object Database {
    val jdub = "com.simple" %% "jdub" % "1.0.0" exclude("nl.grons", "metrics-scala_2.11")
    val postgresJdbc = "org.postgresql" % "postgresql" % "9.4-1201-jdbc41" intransitive()
  }

  object Play {
    val playFilters = filters
    val playWs = ws
    val playJson = json
  }

  object WebJars {
    val requireJs = "org.webjars" % "requirejs" % "2.1.17"
    val bootstrap = "org.webjars" % "bootstrap" % "3.3.4"
  }

  object Metrics {
    val metrics = "nl.grons" %% "metrics-scala" % "3.4.0" withSources()
    val jvm = "io.dropwizard.metrics" % "metrics-jvm" % "3.1.1" withSources()
    val healthChecks = "io.dropwizard.metrics" % "metrics-healthchecks" % "3.1.1" withSources() intransitive()

    val json = "io.dropwizard.metrics" % "metrics-json" % "3.1.1" withSources()

    val jettyServlet = "org.eclipse.jetty" % "jetty-servlet" % "9.3.0.M2" withSources()
    val servlets = "io.dropwizard.metrics" % "metrics-servlets" % "3.1.1" withSources() intransitive()
    val graphite = "io.dropwizard.metrics" % "metrics-graphite" % "3.1.1" withSources() intransitive()
  }

  object Testing {
    val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % "2.3.4"
  }
}
