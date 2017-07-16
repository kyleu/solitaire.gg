package util.web

import javax.inject._

import play.api._
import play.api.http.DefaultHttpErrorHandler
import play.api.mvc.Results._
import play.api.mvc._
import play.api.routing.Router
import util.Logging

import scala.concurrent._

class ErrorHandler @Inject() (
    env: Environment,
    playConfig: Configuration,
    appConfig: util.Config,
    sourceMapper: OptionalSourceMapper,
    router: Provider[Router]
) extends DefaultHttpErrorHandler(env, playConfig, sourceMapper, router) with Logging {

  override def onProdServerError(request: RequestHeader, ex: UsefulException) = Future.successful {
    log.error(s"Error encountered processing request [${request.path}].", ex)
    InternalServerError(views.html.error.serverError(request.path, Some(ex), appConfig.debug)(request.session, request.flash))
  }

  override def onForbidden(request: RequestHeader, message: String) = Future.successful {
    log.info(s"Forbidden access for request [${request.path}]: " + message)
    Forbidden("You're not allowed to access this resource.")
  }

  override protected def onNotFound(request: RequestHeader, message: String) = Future.successful {
    NotFound(views.html.error.notFound(request.path)(request.session, request.flash))
  }

  override protected def onBadRequest(request: RequestHeader, message: String) = Future.successful {
    BadRequest(views.html.error.badRequest(request.path, message)(request.session, request.flash))
  }
}
