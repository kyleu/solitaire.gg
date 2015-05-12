package controllers.admin

import controllers.BaseController
import controllers.BaseController.AuthenticatedAction
import services.test._

object TestController extends BaseController {
  def tests = AuthenticatedAction { implicit request =>
    requireAdmin {
      Ok(views.html.admin.tests())
    }
  }

  def runTest(test: String) = AuthenticatedAction { implicit request =>
    requireAdmin {
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
