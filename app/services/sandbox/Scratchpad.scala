package services.sandbox

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.analytics.AnalyticsExport
import utils.ApplicationContext
import utils.DateUtils._

object Scratchpad extends SandboxTask {
  override def id = "scratchpad"
  override def description = "A one-off I don't feel like putting anwhere else."
  override def run(ctx: ApplicationContext) = {
    val export = new AnalyticsExport(ctx.config)
    val persistedDates = export.getPersistedDateCounts
    persistedDates.map { persisted =>
      val fsd = export.getFileSystemDetails

      val combinedDates = (fsd.map(_._1) ++ persisted.map(_._1)).distinct.sorted
      val messages = combinedDates.map { d =>
        val p = persisted.find(_._1 == d)
        val f = fsd.find(_._1 == d)

        s"$d: ${p.map(_._2)} /  ${f.map(_._2.map(_._2).sum)}"
      }

      messages.mkString("\n")
    }
  }
}
