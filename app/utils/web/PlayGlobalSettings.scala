package utils.web

import play.api.GlobalSettings
import play.api.mvc.{ RequestHeader, WithFilters }
import play.filters.gzip.GzipFilter
import utils.Logging

object PlayGlobalSettings extends WithFilters(PlayLoggingFilter, new GzipFilter()) with GlobalSettings with Logging {
  // Set by ApplicationContext when started.
  var hostname = "not-initialized"

  override def onRouteRequest(request: RequestHeader) = {
    if (!Option(request.path).exists(_.startsWith("/assets"))) {
      log.info(s"Request from [${request.remoteAddress}]: ${request.toString()}")
    }
    super.onRouteRequest(request)
  }
}
