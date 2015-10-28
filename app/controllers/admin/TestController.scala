package controllers.admin

import controllers.BaseController
import services.test._
import utils.ApplicationContext

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

@javax.inject.Singleton
class TestController @javax.inject.Inject() (override val ctx: ApplicationContext) extends BaseController {
  def tests = withAdminSession("list") { implicit request =>
    implicit val identity = request.identity
    Future.successful(Ok(views.html.admin.test.tests()))
  }

  def runTest(test: String) = withAdminSession("run." + test) { implicit request =>
    implicit val identity = request.identity
    Future {
      val testTree = test match {
        case "all" => new AllTests(ctx.supervisor).all
        case "known" => new KnownGameTests(ctx.supervisor).all
        case "poker" => new PokerTests().all

        case "solver" => new SolverTests(ctx.supervisor).all
        case x if x.startsWith("solve-") => new SolverTests(ctx.supervisor).testSolver(x.substring(6)).toTree

        case "variants" => new VariantTests(ctx.supervisor).all
        case x if x.startsWith("variant-") => new VariantTests(ctx.supervisor).testVariant(x.substring(8)).toTree

        case "rules" => new RulesTests().all
        case x if x.startsWith("rules-") => new RulesTests().testGameRules(x.substring(6)).toTree

        case _ => throw new IllegalArgumentException(s"Invalid test [$test].")
      }

      val resultTree = TestService.run(testTree)

      Ok(views.html.admin.test.testResults(resultTree.toSeq()))
    }
  }
}
