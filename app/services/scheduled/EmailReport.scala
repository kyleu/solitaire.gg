package services.scheduled

import models.audit.Metric
import models.queries.report.RowCountQueries
import services.audit.DailyMetricService
import services.database.Database
import services.email.EmailService
import utils.{DateUtils, Logging}
import utils.FutureUtils.defaultContext

import scala.concurrent.Future

class EmailReport(emailService: EmailService) extends ScheduledTask.Task with Logging {
  override def run() = sendReportIfNeeded()

  private[this] def sendReportIfNeeded() = {
    val yesterdayAndBuffer = DateUtils.now.minusDays(1).minusHours(3).toLocalDate
    if (DateUtils.today.minusDays(1) != yesterdayAndBuffer) {
      Future.successful("report" -> None)
    } else {
      DailyMetricService.getMetric(yesterdayAndBuffer, Metric.ReportSent).flatMap { reportSent =>
        if (reportSent.contains(1L)) {
          Future.successful("report" -> None)
        } else {
          log.info(s"Report sent is [$reportSent], sending report for [$yesterdayAndBuffer].")
          val yesterday = DateUtils.today.minusDays(1)
          Database.query(RowCountQueries.ListTables).flatMap { tables =>
            DailyMetricService.getMetrics(yesterday).flatMap { yesterdayMetrics =>
              DailyMetricService.getTotals(yesterday).flatMap { totals =>
                Future.sequence(tables.map(table => Database.query(RowCountQueries.CountTable(table)))).flatMap { counts =>
                  emailService.sendDailyReport(yesterday, yesterdayMetrics._2._1, totals, counts).map { _ =>
                    "report" -> Some(s"Sent report for [$yesterdayAndBuffer]")
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}
