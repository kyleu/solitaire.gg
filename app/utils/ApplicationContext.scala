package utils

import akka.actor.Props
import models.auth.AuthenticationEnvironment
import play.api.Play._
import play.api.http.HttpRequestHandler
import play.api.i18n.MessagesApi
import play.api.inject.ApplicationLifecycle
import play.api.libs.concurrent.Akka
import play.api.mvc.{ Action, RequestHeader, Results }
import play.api.routing.Router
import services.audit.NotificationService
import services.supervisor.ActorSupervisor

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
    val env: AuthenticationEnvironment,
    val config: utils.Config,
    val messagesApi: MessagesApi,
    val lifecycle: ApplicationLifecycle,
    val notificationService: NotificationService
) extends ApplicationContextHelper with Logging {
  start()

  val supervisor = {
    val instanceRef = Akka.system.actorOf(Props(classOf[ActorSupervisor], this), "supervisor")
    log.info(s"Actor Supervisor [${instanceRef.path.toString}] started for [${utils.Config.projectId}].")
    instanceRef
  }
}
