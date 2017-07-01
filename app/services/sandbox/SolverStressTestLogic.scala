package services.sandbox

import utils.FutureUtils.defaultContext
import services.test.{SolverTests, TestService}
import utils.Application

import scala.concurrent.Future

trait SolverStressTestLogic {
  def runTest(): Future[Unit] = Future(TestService.run(new SolverTests().all)).flatMap { _ =>
    runTest()
  }

  def call(ctx: Application) = {
    (0 to 4).foreach(_ => runTest())
    Future.successful("Stress testing!")
  }
}

