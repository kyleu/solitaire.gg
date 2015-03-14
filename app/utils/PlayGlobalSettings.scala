package utils

import java.util.TimeZone

import org.joda.time.DateTimeZone
import play.api.mvc.{RequestHeader, Results}
import play.api.{Logger, Application, GlobalSettings}
import services.database.{DatabaseSchema, DatabaseConnection}

import scala.concurrent.Future

object PlayGlobalSettings extends GlobalSettings with Logging {
  override def onStart(app: Application) {
    Logger.info(utils.Config.projectName + " build [" + BuildInfo.buildinfoBuildnumber + "] is starting.")

    DateTimeZone.setDefault(DateTimeZone.UTC)
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))

//    DatabaseConnection.open()
//    DatabaseSchema.update()

    super.onStart(app)
  }
  override def onStop(app: Application) {
//    DatabaseConnection.close()
    super.onStop(app)
  }

  override def onError(request: RequestHeader, ex: Throwable) = Future.successful(
    Results.InternalServerError(views.html.error.serverError(request.path, Some(ex))(request.session, request.flash))
  )
  override def onHandlerNotFound(request: RequestHeader) = Future.successful(
    Results.NotFound(views.html.error.notFound(request.path)(request.session, request.flash))
  )
  override def onBadRequest(request: RequestHeader, error: String) = Future.successful(
    Results.BadRequest(views.html.error.badRequest(request.path, error)(request.session, request.flash))
  )

  override def onRouteRequest(request: RequestHeader) = {
    if(!request.path.startsWith("/assets")) {
      log.info("Request from [" + request.remoteAddress + "]: " + request.toString)
    }
    super.onRouteRequest(request)
  }
  override def onRequestCompletion(request: RequestHeader) {
    super.onRequestCompletion(request)
  }
}
