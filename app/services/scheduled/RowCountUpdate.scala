package services.scheduled

import scala.concurrent.Future

class RowCountUpdate() extends ScheduledTask.Task {
  override def run() = updateRowCounts()

  private[this] def updateRowCounts() = {
    Future.successful("rowcounts" -> None)
  }
}
