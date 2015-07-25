package services.scheduled

import scala.concurrent.Future

object RowCountUpdate {
  def updateRowCounts() = {
    Future.successful("rowcounts" -> None)
  }
}
