package controllers.admin


import controllers.BaseController
import controllers.BaseController.AuthenticatedAction
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
        case "solver" => ts.testSolver()
        case "variants" => ts.testVariants()
        case x => ts.testVariant(x)
      }
      Ok(views.html.admin.testResults(test, ret.toString(0)))
    }
  }
}
