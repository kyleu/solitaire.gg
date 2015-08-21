package services.scheduled

import scala.concurrent.Future

class TableReaper extends ScheduledTask.Task {
  override def run() = reap()

  private[this] def reap() = {
    Future.successful("reaper" -> None)
  }
}
