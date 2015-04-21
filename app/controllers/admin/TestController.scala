package controllers.admin

import controllers.BaseController
import controllers.BaseController.AuthenticatedAction
import models.game.generated.{ GameRulesSet, Klondike }
import services.test.TestService

object TestController extends BaseController {
  def tests = AuthenticatedAction { implicit request =>
    requireAdmin {
      Ok(views.html.admin.tests())
    }
  }

  def runTest(test: String) = AuthenticatedAction { implicit request =>
    requireAdmin {
      val ts = new TestService()
      val ret = test match {
        case "all" => ts.testAll()

        case "account" => ts.testAccount()

        case "known" => ts.testKnownGame()

        case "solver" => ts.testSolvers()
        case x if x.startsWith("solve-") => ts.testSolver(x.substring(6), verbose = true)

        case "variants" => ts.testVariants()
        case x if x.startsWith("variant-") => ts.testVariant(x.substring(8))

        case "rules" => ts.testAllGameRules()
        case x if x.startsWith("rules-") => ts.testGameRules(GameRulesSet.allById(x.substring(6)))
      }
      Ok(views.html.admin.testResults(test, ret.toString(0)))
    }
  }
}
