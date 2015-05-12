import sbt._

import play.Play.autoImport._

object Dependencies {
  object Database {
    val postgresAsync = "com.github.mauricio" %% "postgresql-async" % "0.2.15"
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

  object Mail {
    val mailer = "com.typesafe.play" %% "play-mailer" % "2.4.1"
  }

  object Authentication {
    val silhouette = "com.mohiva" %% "play-silhouette" % "2.0"
  }

  object Metrics {
    val metrics = "nl.grons" %% "metrics-scala" % "3.5.0" withSources()
    val jvm = "io.dropwizard.metrics" % "metrics-jvm" % "3.1.2" withSources()
    val healthChecks = "io.dropwizard.metrics" % "metrics-healthchecks" % "3.1.2" withSources() intransitive()

    val json = "io.dropwizard.metrics" % "metrics-json" % "3.1.2" withSources()

    val jettyServlet = "org.eclipse.jetty" % "jetty-servlet" % "9.3.0.M2" withSources()
    val servlets = "io.dropwizard.metrics" % "metrics-servlets" % "3.1.2" withSources() intransitive()
    val graphite = "io.dropwizard.metrics" % "metrics-graphite" % "3.1.2" withSources() intransitive()
  }

  object Testing {
    val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % "2.3.4"
  }
}
