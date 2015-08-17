package services.sandbox

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.history.GameHistoryService
import services.user.UserStatisticsService

import scala.concurrent.Future

object ScreenshotCreator extends SandboxTask {
  override def id = "screenshot-creator"
  override def description = "Generates screenshots for completed games."
  override def run() = {
    val ret = screenshots.ScreenshotCreator.processAll()
    // val ret = screenshots.ScreenshotCreator.processRules("klondike")
    Future.successful(ret)
  }
}
