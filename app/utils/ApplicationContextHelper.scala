package utils

import akka.actor.ActorSystem
import com.codahale.metrics.SharedMetricRegistries
import org.joda.time.DateTimeZone
import play.api.Mode
import java.util.TimeZone
import services.database.{ Database, Schema }
import services.scheduled.ScheduledTask
import utils.metrics.Instrumented
import utils.web.PlayGlobalSettings
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

trait ApplicationContextHelper { this: ApplicationContext =>
  protected[this] def start() = {
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

    scheduleTask()

    PlayGlobalSettings.hostname = config.hostname

    lifecycle.addStopHook(() => Future.successful(stop()))
  }

  private[this] def stop() = {
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
