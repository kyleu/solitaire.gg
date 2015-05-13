package controllers.admin

import controllers.BaseController
import models.user.{Role, WithRole}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.game.GameHistoryService

object GameHistoryController extends BaseController {
  def gameList(q: String, sortBy: String) = SecuredAction(WithRole(Role.Admin)).async { implicit request =>
    GameHistoryService.searchGames(q, sortBy).map { games =>
      Ok(views.html.admin.gameList(q, sortBy, games))
    }
  }
}
