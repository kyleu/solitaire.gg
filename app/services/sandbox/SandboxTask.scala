package services.sandbox

import utils.{Logging, ApplicationContext}

import scala.concurrent.Future

trait SandboxTask extends Logging {
  def id: String
  def description: String
  def run(ctx: ApplicationContext): Future[String]
}
