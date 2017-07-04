package controllers.admin

import controllers.BaseController
import services.test._
import utils.Application

import scala.concurrent.Future
import utils.FutureUtils.defaultContext

@javax.inject.Singleton
class TestController @javax.inject.Inject() (override val app: Application) extends BaseController {
  def tests = withAdminSession("list") { implicit request =>
    Future.successful(Ok(views.html.admin.test.tests()))
  }

  def runTest(test: String) = withAdminSession("run." + test) { implicit request =>
    Future {
      val testTree = test match {
        case "all" => AllTests.all
        case "poker" => PokerTests.all

        case "rules" => RulesTests.all
        case x if x.startsWith("rules-") => RulesTests.testGameRules(x.substring(6)).toTree

        case "solver" => SolverTests.all
        case x if x.startsWith("solve-") => SolverTests.testSolver(x.substring(6)).toTree

        case _ => throw new IllegalArgumentException(s"Invalid test [$test].")
      }

      val resultTree = TestService.run(testTree)

      Ok(views.html.admin.test.testResults(resultTree.toSeq()))
    }
  }
}
