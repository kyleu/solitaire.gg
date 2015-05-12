package controllers.admin

import controllers.BaseController
import controllers.BaseController.AdminAction
import services.test._

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

object TestController extends BaseController {
  def tests = AdminAction.async { implicit request =>
    Future.successful(Ok(views.html.admin.tests()))
  }

  def runTest(test: String) = AdminAction.async { implicit request =>
    Future {
      val testTree = test match {
        case "all" => new AllTests().all
        case "known" => new KnownGameTests().all

        case "solver" => new SolverTests().all
        case x if x.startsWith("solve-") => new SolverTests().testSolver(x.substring(6)).toTree

        case "variants" => new VariantTests().all
        case x if x.startsWith("variant-") => new VariantTests().testVariant(x.substring(8)).toTree

        case "rules" => new RulesTests().all
        case x if x.startsWith("rules-") => new RulesTests().testGameRules(x.substring(6)).toTree

        case _ => throw new IllegalArgumentException("Invalid test [" + test + "].")
      }

      val resultTree = TestService.run(testTree)

      Ok(views.html.admin.testResults(resultTree.toSeq()))
    }
  }
}
