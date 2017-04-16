package services.sandbox

import utils.Application

import scala.concurrent.Future

object SendErrorEmail extends SandboxTask {
  override def id = "error-mail"
  override def description = "Send the error email."
  override def run(ctx: Application) = {
    Future.successful("Ok!")
  }
}
