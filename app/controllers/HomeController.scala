package controllers

import controllers.BaseController.AuthenticatedAction
import services.AccountService

object HomeController extends BaseController {
  def index() = AuthenticatedAction { implicit request =>
    Ok(views.html.index(request.accountId, request.name))
  }

  def changeName(name: String) = AuthenticatedAction { implicit request =>
    AccountService.updateAccountName(request.accountId, name)

    Redirect(routes.HomeController.index()).withSession {
      request.session + ("name" -> name)
    }.flashing("success" -> "Name changed.")
  }

  def newDefaultGame() = AuthenticatedAction { implicit request =>
    Ok(views.html.game.gameplay(request.accountId, request.name, "klondike"))
  }

  def newGame(variant: String) = AuthenticatedAction { implicit request =>
    Ok(views.html.game.gameplay(request.accountId, request.name, variant))
  }

  def newGameWithSeed(variant: String, seed: Int) = AuthenticatedAction { implicit request =>
    Ok(views.html.game.gameplay(request.accountId, request.name, variant, None, Some(seed)))
  }

  def newDefaultOfflineGame() = AuthenticatedAction { implicit request =>
    Ok(views.html.game.offline(request.accountId, request.name, "klondike"))
  }

  def newOfflineGame(variant: String) = AuthenticatedAction { implicit request =>
    Ok(views.html.game.offline(request.accountId, request.name, variant))
  }

  def newOfflineGameWithSeed(variant: String, seed: Int) = AuthenticatedAction { implicit request =>
    Ok(views.html.game.offline(request.accountId, request.name, variant, Some(seed)))
  }
}
