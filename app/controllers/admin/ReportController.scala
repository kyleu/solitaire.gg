package controllers.admin

import controllers.BaseController
import models.audit.Metric
import models.queries.history.GameSeedQueries
import models.queries.report.{LeaderboardQueries, RowCountQueries}
import java.time.LocalDate
import services.audit.DailyMetricService
import services.database.Database
import util.FutureUtils.defaultContext
import util.{Application, DateUtils}

import scala.concurrent.Future

@javax.inject.Singleton
class ReportController @javax.inject.Inject() (override val app: Application) extends BaseController {
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

  def leaderboards() = withAdminSession("leaderboards") { implicit request =>
    val empty = Seq.empty[(String, Seq[(java.util.UUID, Option[String], Option[String], Int)])]
    val result = LeaderboardQueries.values.foldLeft(Future.successful(empty)) { (x, y) =>
      x.flatMap { ret =>
        Database.query(y).map { games =>
          ret :+ (y.getClass.getSimpleName.stripSuffix("$") -> games)
        }
      }
    }
    result.map { x =>
      Ok(views.html.admin.report.leaderboards(x))
    }
  }

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

  private[this] def toChartData(metrics: Seq[(java.time.LocalDate, Map[models.audit.Metric, Long])]) = {
    def toChartDataValues(metric: models.audit.Metric) = metrics.map(x => x._1 -> x._2.getOrElse(metric, 0L)).reverseMap { row =>
      val ms = util.DateUtils.toMillis(row._1.atStartOfDay.plusDays(1))
      s"""{ "x": $ms, "y": ${row._2} }"""
    }.mkString(", ")

    val pre = "[\n"
    val post = "\n]\n"
    val content = Metric.values.map { metric =>
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
