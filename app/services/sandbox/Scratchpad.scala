package services.sandbox

import utils.Application

import scala.concurrent.Future

object Scratchpad extends SandboxTask {
  override def id = "scratchpad"
  override def description = "A one-off I don't feel like putting anwhere else."
  override def run(ctx: Application) = {
    Future.successful("Ok")
  }
}
