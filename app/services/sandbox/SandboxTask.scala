package services.sandbox

import utils.ApplicationContext

import scala.concurrent.Future

trait SandboxTask {
  def id: String
  def description: String
  def run(ctx: ApplicationContext): Future[String]
}
