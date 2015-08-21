package services.scheduled

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.audit.DailyMetricService
import utils.DateUtils

class MetricsUpdate extends ScheduledTask.Task {
  override def run() = updateMetrics()

  private[this] def updateMetrics() = {
    val d = DateUtils.today
    DailyMetricService.getMetrics(d).flatMap { oldMetrics =>
      DailyMetricService.recalculateMetrics(d).map { newMetrics =>
        val differences = newMetrics._2._1.toSeq.flatMap { newMetric =>
          val oldMetric = oldMetrics._2._1.getOrElse(newMetric._1, 0L)
          if (oldMetric == newMetric._2) {
            None
          } else {
            Some(newMetric._1.title -> (newMetric._2 - oldMetric))
          }
        }

        if (differences.isEmpty) {
          "metrics" -> None
        } else {
          "metrics" -> Some(s"Updated metrics for [$d], adding [${differences.map(x => x._2 + " " + x._1).mkString(", ")}]")
        }
      }
    }
  }
}
