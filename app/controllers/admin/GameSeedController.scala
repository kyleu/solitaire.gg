package controllers.admin

import controllers.BaseController
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.history.GameSeedService
import utils.Application

@javax.inject.Singleton
class GameSeedController @javax.inject.Inject() (override val app: Application) extends BaseController {
  def list(q: String, sortBy: String, page: Int) = withAdminSession("list") { implicit request =>
    GameSeedService.search(q, sortBy, page).map { seeds =>
      Ok(views.html.admin.seed.list(q, sortBy, seeds._1, page, seeds._2))
    }
  }

  def remove(rules: String, seed: Int) = withAdminSession("remove") { implicit request =>
    GameSeedService.remove(rules, seed).map { ok =>
      val msg = s"Seed [$rules:$seed] removed."
      Redirect(controllers.admin.routes.GameSeedController.list()).flashing("success" -> msg)
    }
  }
}
