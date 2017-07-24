package services.audit

import models.audit.{DailyMetric, Metric}
import models.queries.audit.DailyMetricQueries
import org.joda.time.LocalDate
import util.FutureUtils.defaultContext
import services.database.Database
import util.{DateUtils, Logging}

import scala.concurrent.Future

object DailyMetricService extends Logging {
  def getMetric(d: LocalDate, m: Metric) = Database.query(DailyMetricQueries.GetValue(d, m))

  def getMetrics(d: LocalDate) = Database.query(DailyMetricQueries.GetMetrics(d)).flatMap { m =>
    log.info(s"Found [${m.toSeq.map(x => x._1.toString + ": " + x._2).sorted.mkString(", ")}] metrics for [$d].")
    if (m.size == Metric.values.size) {
      Future.successful((d, (m, 0)))
    } else {
      val missingMetrics = Metric.values.filterNot(m.keySet.contains)
      calculateMetrics(d, missingMetrics).flatMap { metrics =>
        val models = metrics.map(x => DailyMetric(d, x._1, x._2, DateUtils.now)).toSeq
        log.info(s"Inserting [${metrics.size}] missing daily metrics [${metrics.keys.toSeq.map(_.toString).sorted.mkString(", ")}] for [$d].")
        Database.execute(DailyMetricQueries.insertBatch(models)).map { _ =>
          log.info(s"Updated [${metrics.size}] for [$d].")
          (d, (m ++ metrics, metrics.size))
        }
      }
    }
  }

  def getAllMetrics = Database.query(DailyMetricQueries.GetAllMetrics)

  def setMetric(d: LocalDate, metric: Metric, value: Long) = {
    val dm = DailyMetric(d, metric, value, DateUtils.now)
    Database.execute(DailyMetricQueries.UpdateMetric(dm)).flatMap {
      case 1 => Future.successful(dm)
      case 0 => Database.execute(DailyMetricQueries.insert(dm)).map(_ => dm)
      case rowsAffected => throw new IllegalStateException(s"DailyMetric update [$dm] affected [$rowsAffected] rows.")
    }
  }

  def getTotals(last: LocalDate) = Database.query(DailyMetricQueries.GetTotals(last))

  def recalculateMetrics(d: LocalDate) = Database.execute(DailyMetricQueries.RemoveByDay(d)).flatMap { _ =>
    getMetrics(d)
  }

  private[this] def calculateMetrics(d: LocalDate, metrics: Seq[Metric]) = {
    val futures = metrics.map { metric =>
      val f = getSql(metric) match {
        case Some(sql) => Database.query(DailyMetricQueries.CalculateMetric(metric, sql, d))
        case None => metric match {
          case Metric.StorageUsage => Future.successful(getFreeSpace)
          case _ => Future.successful(0L)
        }
      }
      f.map(metric -> _)
    }
    Future.sequence(futures).map(_.toMap)
  }

  private[this] def getSql(metric: Metric) = metric match {
    case Metric.Signups => Some("select count(*) as c from users where created >= ? and created < ?")

    case Metric.GamesStarted => Some("select count(*) as c from games where created >= ? and created < ?")
    case Metric.GamesWon => Some("select count(*) as c from games where created >= ? and created < ? and status in ('w', 'won')")
    case Metric.GamesLost => Some("select count(*) as c from games where created >= ? and created < ? and status in ('l', 'lost')")
    case Metric.GamesAdandoned => Some("select count(*) as c from games where created >= ? and created < ? and status in ('s', 'started')")

    case Metric.Feedbacks => Some("select count(*) as c from user_feedback where occurred >= ? and occurred < ?")
    case _ => None
  }

  private[this] def getFreeSpace = {
    import scala.sys.process._
    val result = "df /".!!
    val lastLine = result.split("\n").last
    val endIndex = lastLine.indexOf('%')
    val startIndex = lastLine.indexOf(' ', endIndex - 5)
    val ret = 100 - lastLine.substring(startIndex, endIndex).trim.toLong
    ret
  }
}
