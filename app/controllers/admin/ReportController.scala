package controllers.admin

import controllers.BaseController
import models.audit.DailyMetric
import models.queries.history.GameSeedQueries
import models.queries.report.RowCountQueries
import org.joda.time.LocalDate
import utils.FutureUtils.defaultContext
import services.audit.DailyMetricService
import services.database.Database
import utils.{Application, DateUtils}

import scala.concurrent.Future

@javax.inject.Singleton
class ReportController @javax.inject.Inject() (override val app: Application) extends BaseController {

  def email(d: LocalDate = DateUtils.today) = withAdminSession("email") { implicit request =>
    Database.query(RowCountQueries.ListTables).flatMap { tables =>
      DailyMetricService.recalculateMetrics(d).flatMap { metrics =>
        DailyMetricService.getTotals(d).flatMap { totals =>
          Future.sequence(tables.map(table => Database.query(RowCountQueries.CountTable(table)))).map { counts =>
            Ok(views.html.admin.report.emailReport(d, metrics._2._1, totals, counts))
          }
        }
      }
    }
  }

  def trend() = withAdminSession("trend") { implicit request =>
    DailyMetricService.getAllMetrics.map { metrics =>
      Ok(views.html.admin.report.trend(metrics, toChartData(metrics)))
    }
  }

  def seed() = withAdminSession("seed") { implicit request =>
    Database.query(GameSeedQueries.SummaryReport).map { report =>
      Ok(views.html.admin.report.seed(report))
    }
  }

  private[this] def toChartData(metrics: Seq[(org.joda.time.LocalDate, Map[models.audit.Metric, Long])]) = {
    def toChartDataValues(metric: models.audit.Metric) = metrics.map(x => x._1 -> x._2.getOrElse(metric, 0L)).reverseMap { row =>
      val ms = utils.DateUtils.toMillis(row._1.toLocalDateTime(org.joda.time.LocalTime.MIDNIGHT).plusDays(1))
      s"""{ "x": $ms, "y": ${row._2} }"""
    }.mkString(", ")

    val pre = "[\n"
    val post = "\n]\n"
    val content = DailyMetric.all.map { metric =>
      s"""
      |  {
      |    "values": [ ${toChartDataValues(metric)} ],
      |    "key": "${metric.title}"
      |  }
      """.stripMargin
    }.mkString(",\n")
    pre + content + post
  }
}
