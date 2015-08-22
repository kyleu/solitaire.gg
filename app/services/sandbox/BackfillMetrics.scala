package services.sandbox

import models.queries.history.RequestLogQueries
import org.joda.time.LocalDate
import services.audit.DailyMetricService
import services.database.Database
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import utils.{ ApplicationContext, DateUtils }

import scala.concurrent.Future

object BackfillMetrics extends SandboxTask {
  override def id = "backfill-metrics"
  override def description = "Backfill missing daily metrics."
  override def run(ctx: ApplicationContext) = {
    def getDays(d: LocalDate) = {
      val today = DateUtils.today
      val start = (d.getYear * 365) + d.getDayOfYear
      val end = (today.getYear * 365) + today.getDayOfYear
      val numDays = end - start
      Future.successful((0 to numDays).map(i => today.minusDays(i)))
    }

    for {
      startDay <- Database.query(RequestLogQueries.GetEarliestDay)
      days <- getDays(startDay)
      result <- Future.sequence(days.map(d => DailyMetricService.getMetrics(d)))
    } yield result.map(x => s"${x._1}: ${x._2._1.size} metrics, ${x._2._2} calculated.").mkString("\n")
  }
}
