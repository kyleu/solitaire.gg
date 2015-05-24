package controllers.admin

import controllers.BaseController
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.game.GameHistoryService

object GameHistoryController extends BaseController {
  def gameList(q: String, sortBy: String) = withAdminSession { implicit request =>
    GameHistoryService.searchGames(q, sortBy).map { games =>
      Ok(views.html.admin.game.gameList(q, sortBy, games))
    }
  }
}
