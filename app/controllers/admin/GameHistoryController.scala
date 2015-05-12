package controllers.admin

import controllers.BaseController
import controllers.BaseController.AdminAction
import services.game.GameHistoryService

import play.api.libs.concurrent.Execution.Implicits.defaultContext

object GameHistoryController extends BaseController {
  def gameList(q: String, sortBy: String) = AdminAction.async { implicit request =>
    GameHistoryService.searchGames(q, sortBy).map { games =>
      Ok(views.html.admin.gameList(q, sortBy, games))
    }
  }
}
