package services.sandbox

import akka.actor.ActorRef
import services.test.{ SolverTests, TestService }
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import utils.ApplicationContext

import scala.concurrent.Future

object SolverStressTest extends SandboxTask {
  override def id = "solver-test"
  override def description = "Infinite stress test for the poor solver."
  override def run(ctx: ApplicationContext) = {
    def runTest(): Future[Unit] = {
      Future(TestService.run(new SolverTests(ctx.supervisor).all)).flatMap { result =>
        runTest()
      }
    }

    runTest()
    Future.successful("OK, churning...")
  }
}
