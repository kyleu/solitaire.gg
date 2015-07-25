package services.scheduled

import scala.concurrent.Future

object TableReaper {
  def reap() = {
    Future.successful("reaper" -> None)
  }
}
