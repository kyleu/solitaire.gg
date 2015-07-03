package controllers.admin

import controllers.BaseController
import play.api.i18n.MessagesApi
import services.test._
import services.user.AuthenticationEnvironment

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

@javax.inject.Singleton
class TestController @javax.inject.Inject() (
    override val messagesApi: MessagesApi,
    override val env: AuthenticationEnvironment
) extends BaseController {
  def tests = withAdminSession { implicit request =>
    Future.successful(Ok(views.html.admin.test.tests()))
  }

  def runTest(test: String) = withAdminSession { implicit request =>
    Future {
      val testTree = test match {
        case "all" => new AllTests().all
        case "known" => new KnownGameTests().all
        case "poker" => new PokerTests().all

        case "solver" => new SolverTests().all
        case x if x.startsWith("solve-") => new SolverTests().testSolver(x.substring(6)).toTree

        case "variants" => new VariantTests().all
        case x if x.startsWith("variant-") => new VariantTests().testVariant(x.substring(8)).toTree

        case "rules" => new RulesTests().all
        case x if x.startsWith("rules-") => new RulesTests().testGameRules(x.substring(6)).toTree

        case _ => throw new IllegalArgumentException(s"Invalid test [$test].")
      }

      val resultTree = TestService.run(testTree)

      Ok(views.html.admin.test.testResults(resultTree.toSeq()))
    }
  }
}
