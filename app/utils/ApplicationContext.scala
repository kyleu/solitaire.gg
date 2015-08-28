package utils

import java.util.TimeZone

import akka.actor.{ ActorSystem, Props }
import com.codahale.metrics.SharedMetricRegistries
import models.auth.AuthenticationEnvironment
import org.joda.time.DateTimeZone
import play.api.Mode
import play.api.Play._
import play.api.http.HttpRequestHandler
import play.api.i18n.MessagesApi
import play.api.inject.ApplicationLifecycle
import play.api.libs.concurrent.Akka
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.ws.WSClient
import play.api.mvc.{ Action, RequestHeader, Results }
import play.api.routing.Router
import services.database.{ Database, Schema }
import services.scheduled.ScheduledTask
import services.spark.SparkService
import services.supervisor.ActorSupervisor
import utils.metrics.Instrumented
import utils.web.PlayGlobalSettings

import scala.concurrent.Future

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
    lifecycle: ApplicationLifecycle
) extends Logging {
  start()

  lazy val supervisor = {
    val instanceRef = Akka.system.actorOf(Props(classOf[ActorSupervisor], this), "supervisor")
    log.info(s"Actor Supervisor [${instanceRef.path.toString}] started for [${utils.Config.projectId}].")
    instanceRef
  }

  private[this] def start() = {
    if(ApplicationContext.initialized) {
      throw new IllegalStateException("ApplicationContext is already initialized.")
    }
    ApplicationContext.initialized = true

    log.info(s"${Config.projectName} is starting on [${config.hostname}].")

    DateTimeZone.setDefault(DateTimeZone.UTC)
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))

    SharedMetricRegistries.remove("default")
    SharedMetricRegistries.add("default", Instrumented.metricRegistry)

    Database.open(config.databaseConfiguration)
    Schema.update()

    if(config.sparkEnabled) {
      SparkService.start(config.sparkMaster)
    }

    scheduleTask()

    PlayGlobalSettings.hostname = config.hostname

    lifecycle.addStopHook(() => Future.successful(stop()))
  }

  private[this] def stop() = {
    if(config.sparkEnabled) {
      SparkService.stop()
    }
    Database.close()
    SharedMetricRegistries.remove("default")
  }

  private[this] def scheduleTask() = {
    import play.api.Play.{ current => app }

    import scala.concurrent.duration._

    if (app.mode == Mode.Dev) {
      log.info("Dev mode, so not starting scheduled task.")
    } else {
      log.info("Scheduling task to run every minute, after five minutes.")
      val task = app.injector.instanceOf[ScheduledTask]
      val system = app.injector.instanceOf[ActorSystem]
      system.scheduler.schedule(5.minutes, 1.minute, task)
    }
  }
}
