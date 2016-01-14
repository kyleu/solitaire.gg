package services.scheduled

import models.audit.DailyMetric
import models.queries.report.RowCountQueries
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.audit.DailyMetricService
import services.database.Database
import services.email.EmailService
import utils.DateUtils

import scala.concurrent.Future

class EmailReport(emailService: EmailService) extends ScheduledTask.Task {
  override def run() = sendReportIfNeeded()

  private[this] def sendReportIfNeeded() = {
    val yesterdayAndBuffer = DateUtils.now.minusDays(1).minusHours(3).toLocalDate
    if (DateUtils.today.minusDays(1) != yesterdayAndBuffer) {
      Future.successful("report" -> None)
    } else {
      DailyMetricService.getMetric(yesterdayAndBuffer, DailyMetric.ReportSent).flatMap { reportSent =>
        if (reportSent.contains(1L)) {
          Future.successful("report" -> None)
        } else {
          val yesterday = DateUtils.today.minusDays(1)
          for {
            tables <- Database.query(RowCountQueries.ListTables)
            yesterdayMetrics <- DailyMetricService.getMetrics(yesterday)
            totals <- DailyMetricService.getTotals(yesterday)
            counts <- Future.sequence(tables.map(table => Database.query(RowCountQueries.CountTable(table))))
            report <- emailService.sendDailyReport(yesterday, "greyblue", yesterdayMetrics._2._1, totals, counts)
          } yield {
            "report" -> Some(s"Sent report for [$yesterdayAndBuffer]")
          }
        }
      }
    }
  }
}
