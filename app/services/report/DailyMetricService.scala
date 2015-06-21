package services.report

import models.audit.DailyMetric
import models.audit.DailyMetric._
import models.database.queries.DailyMetricQueries
import org.joda.time.{ LocalDateTime, LocalDate }
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

import scala.concurrent.Future

object DailyMetricService {
  def getMetric(d: LocalDate, m: DailyMetric.Metric) = Database.query(DailyMetricQueries.GetValue(d, m))

  def getMetrics(d: LocalDate) = Database.query(DailyMetricQueries.GetMetrics(d)).flatMap { m =>
    if (m.size == DailyMetric.all.size) {
      Future.successful(m)
    } else {
      val missingMetrics = DailyMetric.all.filterNot(m.keySet.contains)
      calculateMetrics(d, missingMetrics).map { metrics =>
        val models = metrics.map(x => DailyMetric(d, x._1, x._2, new LocalDateTime())).toSeq
        Database.execute(DailyMetricQueries.insertBatch(models))
        m ++ metrics
      }
    }
  }

  def setMetric(d: LocalDate, metric: DailyMetric.Metric, value: Long) = {
    val dm = DailyMetric(d, metric, value, new LocalDateTime())
    Database.execute(DailyMetricQueries.UpdateMetric(dm)).flatMap { rowsAffected =>
      if (rowsAffected == 1) {
        Future.successful(dm)
      } else {
        Database.execute(DailyMetricQueries.insert(dm)).map(x => dm)
      }
    }
  }

  def getTotals(last: LocalDate) = Database.query(DailyMetricQueries.GetTotals(last))

  private[this] def calculateMetrics(d: LocalDate, metrics: Seq[DailyMetric.Metric]) = {
    val futures = metrics.map { metric =>
      getSql(metric) match {
        case Some(sql) => Database.query(DailyMetricQueries.CalculateMetric(metric, sql, d))
        case None => Future.successful(metric -> 0L)
      }
    }
    Future.sequence(futures).map(_.toMap)
  }

  private[this] def getSql(metric: Metric) = metric match {
    case GamesAdandoned => Some("select count(*) as c from games where created >= ? and created < ? and status = 'abandoned'")
    case GamesStarted => Some("select count(*) as c from games where created >= ? and created < ?")
    case GamesWon => Some("select count(*) as c from games where created >= ? and created < ? and status = 'win'")

    case Requests => Some("select count(*) as c from requests where started >= ? and started < ?")
    case Signups => Some("select count(*) as c from users where created >= ? and created < ?")

    case _ => None
  }
}
