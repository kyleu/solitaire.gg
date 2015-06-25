package services.sandbox

import services.test.{ SolverTests, TestService }
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

object SolverStressTest {
  private[this] var runsCompleted = 0
  def run() = {
    def runTest(): Future[Unit] = {
      Future(TestService.run(new SolverTests().all)).flatMap { result =>
        runsCompleted += 1
        runTest()
      }
    }

    runTest()
    Future.successful("OK, churning...")
  }
}
