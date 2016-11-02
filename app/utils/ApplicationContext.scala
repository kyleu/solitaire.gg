package utils

import akka.actor.ActorSystem
import play.api.http.HttpRequestHandler
import play.api.i18n.MessagesApi
import play.api.inject.ApplicationLifecycle
import play.api.mvc.{Action, RequestHeader, Results}
import play.api.routing.Router
import services.audit.NotificationService
import services.scheduled.ScheduledTask

object ApplicationContext {
  var initialized = false

  class SimpleHttpRequestHandler @javax.inject.Inject() (router: Router) extends HttpRequestHandler {
    def handlerForRequest(request: RequestHeader) = {
      router.routes.lift(request) match {
        case Some(handler) => (request, handler)
        case None => (request, Action(Results.NotFound))
      }
    }
  }
}

@javax.inject.Singleton
class ApplicationContext @javax.inject.Inject() (
    val config: utils.Config,
    val messagesApi: MessagesApi,
    val lifecycle: ApplicationLifecycle,
    val notificationService: NotificationService,
    val system: ActorSystem,
    val task: ScheduledTask
) extends ApplicationContextHelper with Logging {
  start()
}
