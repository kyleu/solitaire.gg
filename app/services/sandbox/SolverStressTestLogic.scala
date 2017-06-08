package services.sandbox

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.test.{SolverTests, TestService}
import utils.Application

import scala.concurrent.Future

trait SolverStressTestLogic {
  def runTest(): Future[Unit] = Future(TestService.run(new SolverTests().all)).flatMap { _ =>
    runTest()
  }

  def run(ctx: Application) = {
    (0 to 4).foreach(_ => runTest())
    Future.successful("Stress testing!")
  }
}

