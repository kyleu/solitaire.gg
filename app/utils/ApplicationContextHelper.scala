package utils

import akka.actor.ActorSystem
import com.codahale.metrics.SharedMetricRegistries
import org.joda.time.DateTimeZone
import java.util.TimeZone
import services.database.{Database, Schema}
import services.scheduled.ScheduledTask
import utils.metrics.Instrumented
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

trait ApplicationContextHelper { this: ApplicationContext =>
  protected[this] def start() = {
    if (ApplicationContext.initialized) {
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

    if (!config.debug) {
      scheduleTask(task, system)
    }

    lifecycle.addStopHook(() => Future.successful(stop()))
  }

  private[this] def stop() = {
    Database.close()
    SharedMetricRegistries.remove("default")
  }

  private[this] def scheduleTask(task: ScheduledTask, system: ActorSystem) = {
    import scala.concurrent.duration._
    log.info("Scheduling task to run every minute, after five minutes.")
    system.scheduler.schedule(5.minutes, 1.minute, task)
  }
}
