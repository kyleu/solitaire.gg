package utils

import play.api.mvc.{RequestHeader, Results}
import play.api.{Application, GlobalSettings}
import services.ActorSupervisor

import scala.concurrent.Future

object PlayGlobalSettings extends GlobalSettings with Logging {
  override def onStart(app: Application) {
    ActorSupervisor.start()
    super.onStart(app)
  }
  override def onStop(app: Application) {
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
