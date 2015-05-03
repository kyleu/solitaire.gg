package controllers

import controllers.BaseController.AuthenticatedAction
import models.game.rules.GameRulesSet
import services.AccountService

object HomeController extends BaseController {
  def index() = AuthenticatedAction { implicit request =>
    Ok(views.html.index(request.accountId, request.name))
  }

  def sandbox() = AuthenticatedAction { implicit request =>
    Ok(views.html.sandbox())
  }

  def untrail(path: String) = AuthenticatedAction {
    MovedPermanently("/" + path)
  }

  def changeName(name: String) = AuthenticatedAction { implicit request =>
    AccountService.updateAccountName(request.accountId, name)

    Redirect(routes.HomeController.index()).withSession {
      request.session + ("name" -> name)
    }.flashing("success" -> "Name changed.")
  }

  def help(id: String) = AuthenticatedAction { implicit request =>
    GameRulesSet.allById.get(id) match {
      case Some(rules) => Ok(views.html.help(rules))
      case None => Ok("We can't find and information about that game.")
    }
  }

  def newDefaultGame() = AuthenticatedAction { implicit request =>
    Ok(views.html.game.gameplay(request.accountId, request.name, "klondike"))
  }

  def newGame(rules: String) = AuthenticatedAction { implicit request =>
    Ok(views.html.game.gameplay(request.accountId, request.name, rules))
  }

  def newGameWithSeed(rules: String, seed: Int) = AuthenticatedAction { implicit request =>
    Ok(views.html.game.gameplay(request.accountId, request.name, rules, None, Some(seed)))
  }

  def newDefaultOfflineGame() = AuthenticatedAction { implicit request =>
    Ok(views.html.game.gameplay(request.accountId, request.name, "klondike", offline = true))
  }

  def newOfflineGame(rules: String) = AuthenticatedAction { implicit request =>
    Ok(views.html.game.gameplay(request.accountId, request.name, rules, offline = true))
  }

  def newOfflineGameWithSeed(rules: String, seed: Int) = AuthenticatedAction { implicit request =>
    Ok(views.html.game.gameplay(request.accountId, request.name, rules, seed = Some(seed), offline = true))
  }
}
