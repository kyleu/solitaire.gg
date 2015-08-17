package services.sandbox

import scala.concurrent.Future

trait SandboxTask {
  def id: String
  def description: String
  def run(): Future[String]
}
