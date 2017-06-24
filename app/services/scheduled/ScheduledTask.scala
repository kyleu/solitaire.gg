package services.scheduled

import utils.FutureUtils.defaultContext
import services.email.EmailService
import utils.Logging

import scala.concurrent.Future
import scala.util.{Failure, Success}

object ScheduledTask {
  trait Task {
    def run(): Future[(String, Option[String])]
  }
}

@javax.inject.Singleton
class ScheduledTask @javax.inject.Inject() (emailService: EmailService, config: utils.Config) extends Logging with Runnable {
  private[this] var running = false

  private[this] val tasks = Seq(
    new MetricsUpdate(),
    new EmailReport(emailService),
    new RowCountUpdate(),
    new TableReaper()
  )

  override def run() = go(false)

  def go(force: Boolean) = {
    if (running) {
      Future.failed(new RuntimeException("Scheduled task already running."))
    } else if (config.debug && !force) {
      Future.successful(Nil)
    } else {
      running = true
      val startMs = System.currentTimeMillis
      val f = Future.sequence(tasks.map(_.run()))
      f.onComplete {
        case Success(_) => running = false
        case Failure(t) =>
          log.warn("Exception encountered running scheduled tasks.", t)
          running = false
      }
      f.map { ret =>
        val duration = System.currentTimeMillis - startMs
        val actions = ret.filter(_._2.isDefined)
        val msgStart = s"Completed [${ret.size}] scheduled tasks in [${duration}ms]"
        if (actions.nonEmpty) {
          val result = ret.map(x => s"${x._1}: ${x._2.getOrElse("No progress")}").mkString(", ")
          log.info(s"$msgStart with result [$result].")
        } else {
          log.debug(s"$msgStart. No result.")
        }
        ret
      }
    }
  }
}
