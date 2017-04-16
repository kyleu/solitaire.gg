package controllers.admin

import controllers.BaseController
import services.test._
import utils.Application

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

@javax.inject.Singleton
class TestController @javax.inject.Inject() (override val app: Application) extends BaseController {
  def tests = withAdminSession("list") { implicit request =>
    Future.successful(Ok(views.html.admin.test.tests()))
  }

  def runTest(test: String) = withAdminSession("run." + test) { implicit request =>
    Future {
      val testTree = test match {
        case "all" => new AllTests().all
        case "poker" => new PokerTests().all

        case "rules" => new RulesTests().all
        case x if x.startsWith("rules-") => new RulesTests().testGameRules(x.substring(6)).toTree

        case _ => throw new IllegalArgumentException(s"Invalid test [$test].")
      }

      val resultTree = TestService.run(testTree)

      Ok(views.html.admin.test.testResults(resultTree.toSeq()))
    }
  }
}
