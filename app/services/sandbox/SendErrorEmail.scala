package services.sandbox

import utils.ApplicationContext

import scala.concurrent.Future

object SendErrorEmail extends SandboxTask {
  override def id = "error-mail"
  override def description = "Send the error email."
  override def run(ctx: ApplicationContext) = {
    Future.successful("Ok!")
  }
}
