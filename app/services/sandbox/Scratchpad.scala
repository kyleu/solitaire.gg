package services.sandbox

import utils.ApplicationContext

import scala.concurrent.Future

object Scratchpad extends SandboxTask {
  override def id = "scratchpad"
  override def description = "A one-off I don't feel like putting anwhere else."
  override def run(ctx: ApplicationContext) = {
    Future.successful("Ok")
  }
}
