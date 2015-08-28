package utils.web

import play.api.GlobalSettings
import play.api.http.HeaderNames
import play.api.mvc.{ Action, RequestHeader, Results, WithFilters }
import play.filters.gzip.GzipFilter
import utils.Logging

object PlayGlobalSettings extends WithFilters(PlayLoggingFilter, new GzipFilter()) with GlobalSettings with Logging {
  // Set by ApplicationContext when started.
  var hostname = "not-initialized"

  override def onRouteRequest(request: RequestHeader) = {
    if (!Option(request.path).exists(_.startsWith("/assets"))) {
      log.info(s"Request from [${request.remoteAddress}]: ${request.toString()}")
    }
    if (request.domain == hostname) {
      super.onRouteRequest(request)
    } else {
      Some(redirectToBareDomain(request))
    }
  }

  private[this] def redirectToBareDomain(request: RequestHeader) = Action {
    val protocol = if (request.secure) { "https" } else { "http" }
    Results.MovedPermanently(s"$protocol://$hostname${request.path}").withHeaders(HeaderNames.CACHE_CONTROL -> "public, max-age=31556926")
  }
}
