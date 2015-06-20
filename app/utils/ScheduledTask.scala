package utils

import models.audit.DailyMetric
import models.database.queries.ReportQueries
import org.joda.time.LocalDate
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.EmailService
import services.database.Database
import services.report.DailyMetricService

import scala.concurrent.Future

@javax.inject.Singleton
class ScheduledTask @javax.inject.Inject() (emailService: EmailService) extends Logging with Runnable {
  private[this] var running = false

  override def run() = go()

  def go() = {
    if (running) {
      Future.failed(new RuntimeException("Scheduled task already running."))
    } else if(utils.Config.debug) {
      Future.successful(Nil)
    } else {
      running = true
      val startMs = System.currentTimeMillis
      val f = Future.sequence(Seq(
        // updateMetrics()
        sendReportIfNeeded()
        // updateCounts()
        // reapTables()
      ))
      f.onFailure {
        case t: Throwable =>
          log.warn("Exception encountered running scheduled tasks.", t)
          running = false;
      }
      f.onSuccess {
        case _ => running = false
      }
      f.map { ret =>
        val duration = System.currentTimeMillis - startMs
        val actions = ret.filter(_._2.isDefined)
        val msgStart = s"Completed [${ret.size}] scheduled tasks in [${duration}ms]"
        if(actions.nonEmpty) {
          val result = ret.map(x => s"${x._1}: ${x._2.getOrElse("No progress")}").mkString(", ")
          log.info(s"$msgStart with result [$result].")
        } else {
          log.debug(s"$msgStart. No result.")
        }
        ret
      }
    }
  }

  private[this] def sendReportIfNeeded() = {
    val yesterday = new LocalDate().minusDays(1)
    DailyMetricService.getMetric(yesterday, DailyMetric.ReportSent).flatMap { reportSent =>
      if (reportSent.contains(1L)) {
        Future.successful("report" -> None)
      } else {
        Database.query(ReportQueries.ListTables).flatMap { tables =>
          Future.sequence(tables.map(table => Database.query(ReportQueries.CountTable(table)))).flatMap { counts =>
            val today = new LocalDate()
            val yesterday = today.minusDays(1)
            DailyMetricService.getMetrics(yesterday).flatMap { yesterdayMetrics =>
              emailService.sendDailyReport(yesterday, "greyblue", yesterdayMetrics, counts)
              DailyMetricService.setMetric(yesterday, DailyMetric.ReportSent, 1L).map { x =>
                "report" -> Some("Sent report")
              }
            }
          }
        }
      }
    }
  }
}
