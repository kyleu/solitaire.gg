package services.sandbox

import utils.ApplicationContext

import scala.concurrent.Future

object ScreenshotCreator extends SandboxTask {
  override def id = "screenshot-creator"
  override def description = "Generates screenshots for completed games."
  override def run(ctx: ApplicationContext) = {
    val ret = screenshots.ScreenshotCreator.processAll()
    // val ret = screenshots.ScreenshotCreator.processRules("klondike")
    Future.successful(ret)
  }
}
