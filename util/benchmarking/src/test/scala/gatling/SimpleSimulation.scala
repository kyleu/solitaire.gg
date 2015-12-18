package gatling

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

class SimpleSimulation extends Simulation {
  val httpConf = http
    .baseURL("http://solitaire.gg")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val scn = scenario("Basic Scenario").exec(http("Home Page").get("/"))

  setUp(scn.inject(atOnceUsers(1)).protocols(httpConf))
}
