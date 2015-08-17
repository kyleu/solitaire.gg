package services.sandbox

import services.test.{ SolverTests, TestService }
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

object SolverStressTest extends SandboxTask {
  override def id = "solver-test"
  override def description = "Infinite stress test for the poor solver."
  override def run() = {
    def runTest(): Future[Unit] = {
      Future(TestService.run(new SolverTests().all)).flatMap { result =>
        runTest()
      }
    }

    runTest()
    Future.successful("OK, churning...")
  }
}
