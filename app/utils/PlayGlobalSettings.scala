package utils

import java.util.TimeZone

import com.codahale.metrics.SharedMetricRegistries
import org.joda.time.DateTimeZone
import play.api.http.HeaderNames
import play.api.mvc.{Action, RequestHeader, Results, WithFilters}
import play.api.{Application, GlobalSettings, Mode}
import play.filters.gzip.GzipFilter
import play.filters.headers.SecurityHeadersFilter
import services.ActorSupervisor
import services.database.{Database, Schema}
import utils.metrics.Instrumented

import scala.concurrent.Future

object PlayGlobalSettings extends WithFilters(PlayLoggingFilter, SecurityHeadersFilter(), new GzipFilter()) with GlobalSettings with Logging {
  override def onStart(app: Application) {
    log.info(utils.Config.projectName + " build [" + BuildInfo.buildinfoBuildnumber + "] is starting on [" + utils.Config.hostname + "].")

    SharedMetricRegistries.remove("default")
    SharedMetricRegistries.add("default", Instrumented.metricRegistry)

    DateTimeZone.setDefault(DateTimeZone.UTC)
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))

    Database.open()
    Schema.update()
    ActorSupervisor.instance

    super.onStart(app)
  }
  override def onStop(app: Application) {
    Database.close()

    super.onStop(app)
  }

  override def onError(request: RequestHeader, ex: Throwable) = if (play.api.Play.current.mode == Mode.Dev) {
    super.onError(request, ex)
  } else {
    Future.successful(
      Results.InternalServerError(views.html.error.serverError(request.path, Some(ex))(request.session, request.flash))
    )
  }
  override def onHandlerNotFound(request: RequestHeader) = Future.successful(
    Results.NotFound(views.html.error.notFound(request.path)(request.session, request.flash))
  )
  override def onBadRequest(request: RequestHeader, error: String) = Future.successful(
    Results.BadRequest(views.html.error.badRequest(request.path, error)(request.session, request.flash))
  )

  override def onRouteRequest(request: RequestHeader) = {
    if (!request.path.startsWith("/assets")) {
      log.info("Request from [" + request.remoteAddress + "]: " + request.toString)
    }
    if(request.domain == utils.Config.hostname) {
      super.onRouteRequest(request)
    } else {
      Some(redirectToBareDomain(request))
    }
  }

  private[this] def redirectToBareDomain(request: RequestHeader) = Action {
    val protocol = if(request.secure) { "https" } else { "http" }
    Results.MovedPermanently(s"$protocol://${utils.Config.hostname}${request.path}").withHeaders(HeaderNames.CACHE_CONTROL -> "public, max-age=31556926")
  }
}
