package services.sandbox

import utils.{Logging, Application}

import scala.concurrent.Future

trait SandboxTask extends Logging {
  def id: String
  def description: String
  def run(ctx: Application): Future[String]
}
