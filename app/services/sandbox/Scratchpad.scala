package services.sandbox

import scala.concurrent.Future

object Scratchpad extends SandboxTask {
  override def id = "scratchpad"
  override def description = "A one-off I don't feel like putting anwhere else."
  override def run() = {
    val ret = "No op."
    Future.successful("Ok: " + ret)
  }
}
