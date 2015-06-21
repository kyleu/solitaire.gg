package services.scheduled

import models.audit.DailyMetric
import models.database.queries.ReportQueries
import org.joda.time.{ LocalDate, LocalDateTime }
import services.EmailService
import services.database.Database
import services.report.DailyMetricService

import scala.concurrent.Future

object EmailReport {
  def sendReportIfNeeded(emailService: EmailService) = {
    val yesterdayAndBuffer = new LocalDateTime().minusDays(1).plusHours(3).toLocalDate
    DailyMetricService.getMetric(yesterdayAndBuffer, DailyMetric.ReportSent).flatMap { reportSent =>
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
