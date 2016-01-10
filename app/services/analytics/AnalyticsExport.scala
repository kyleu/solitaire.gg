package services.analytics

import models.queries.audit.AnalyticsEventQueries
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import utils.Config
import utils.DateUtils._

class AnalyticsExport(cfg: Config) {
  val fileService = new AnalyticsFileService(cfg)

  def getPersistedDateCounts = Database.query(AnalyticsEventQueries.GetDateCounts)

  def getStatus = getPersistedDateCounts.map { persisted =>
    val fsd = fileService.getFileSystemDetails
    val combinedDates = (fsd.map(_._1) ++ persisted.map(_._1)).distinct.sorted
    combinedDates.map { d =>
      val p = persisted.find(_._1 == d)
      val f = fsd.find(_._1 == d)
      (d, p.map(_._2).getOrElse(0), f.map(_._2).getOrElse(Nil))
    }
  }
}
