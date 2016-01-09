package services.sandbox

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.analytics.AnalyticsExport
import utils.ApplicationContext

object Scratchpad extends SandboxTask {
  override def id = "scratchpad"
  override def description = "A one-off I don't feel like putting anwhere else."
  override def run(ctx: ApplicationContext) = {
    val export = new AnalyticsExport()
    val persistedDates = export.getPersistedDateCounts
    persistedDates.map { dates =>
      "Days: " + dates.mkString(", ")
    }
  }
}
