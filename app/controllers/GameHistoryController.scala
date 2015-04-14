package controllers

import controllers.BaseController.AuthenticatedAction

object GameHistoryController extends BaseController {
  def gameList(q: String) = AuthenticatedAction { implicit request =>
    val games = Seq.empty[Any]
    Ok(views.html.admin.gameList(q, games))
  }
}
