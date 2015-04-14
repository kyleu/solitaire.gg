package controllers

import controllers.BaseController.AuthenticatedAction
import services.GameHistoryService

object GameHistoryController extends BaseController {
  def gameList(q: String) = AuthenticatedAction { implicit request =>
    val games = GameHistoryService.searchGames(q)
    Ok(views.html.admin.gameList(q, games))
  }
}
