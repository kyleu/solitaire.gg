package utils

import models.audit.DailyMetric
import models.database.queries.ReportQueries
import org.joda.time.LocalDate
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.mailer.MailerClient
import services.EmailService
import services.database.Database
import services.report.DailyMetricService

import scala.concurrent.Future

@javax.inject.Singleton
class ScheduledTask @javax.inject.Inject() (mailer: MailerClient) extends Logging {
  private[this] var running = false

  private[this] val emailService = new EmailService(mailer)

  def go() = {
    if (running) {
      Future.failed(new RuntimeException("Scheduled task already running."))
    } else {
      running = true
      val f = Future.sequence(Seq(
        sendReportIfNeeded()
      ))
      f.onFailure {
        case t: Throwable =>
          log.warn("Exception encountered running scheduled tasks.", t)
          running = false;
      }
      f.onSuccess {
        case _ => running = false
      }
      f
    }
  }

  private[this] def sendReportIfNeeded() = {
    val yesterday = new LocalDate().minusDays(1)
    DailyMetricService.getMetric(yesterday, DailyMetric.ReportSent).flatMap { reportSent =>
      if (reportSent == 1L) {
        Future.successful("Ok: Report already sent.")
      } else {
        Database.query(ReportQueries.ListTables).flatMap { tables =>
          Future.sequence(tables.map(table => Database.query(ReportQueries.CountTable(table)))).flatMap { counts =>
            val today = new LocalDate()
            val yesterday = today.minusDays(1)
            DailyMetricService.getMetrics(yesterday).map { yesterdayMetrics =>
              emailService.sendDailyReport(yesterday, "greyblue", yesterdayMetrics, counts)
              "Ok: Sent report."
            }
          }
        }
      }
    }
  }
}
