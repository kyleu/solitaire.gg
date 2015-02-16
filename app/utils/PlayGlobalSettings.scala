package utils

import play.api.mvc.{RequestHeader, Results}
import play.api.{Logger, Application, GlobalSettings}
import services.ActorSupervisor

import scala.concurrent.Future

object PlayGlobalSettings extends GlobalSettings {
  override def onStart(app: Application) = {
    ActorSupervisor.start()
    super.onStart(app)
  }
  override def onStop(app: Application) = {
    super.onStop(app)
  }

//  override def onError(request: RequestHeader, ex: Throwable) = Future.successful(
//    Results.InternalServerError(TemplateHelper.tagToHtml(views.error.ServerErrorTemplate(request.path, ex)))
//  )
//  override def onHandlerNotFound(request: RequestHeader) = Future.successful(
//    Results.NotFound(TemplateHelper.tagToHtml(views.error.PageNotFoundTemplate(request.path)))
//  )
//  override def onBadRequest(request: RequestHeader, error: String) = Future.successful(
//    Results.BadRequest(TemplateHelper.tagToHtml(views.error.BadRequestTemplate(request.path, error)))
//  )

  override def onRouteRequest(request: RequestHeader) = {
    if(!request.path.startsWith("/assets")) {
      Logger.info("Request from [" + request.remoteAddress + "]: " + request.toString)
    }
    super.onRouteRequest(request)
  }
  override def onRequestCompletion(request: RequestHeader) = {
    super.onRequestCompletion(request)
  }
}
