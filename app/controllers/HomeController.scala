package controllers

import java.util.UUID

import play.api.mvc._
import services.AccountService

object HomeController extends Controller {
  def index() = Action { implicit request =>
    val (accountId, name) = AccountService.getAccount(request.session.data)
    Ok(views.html.index(accountId, name)).withSession {
      request.session + ("account" -> accountId.toString) + ("name" -> name)
    }
  }

  def changeName(name: String) = Action { implicit request =>
    val accountId = UUID.fromString(request.session("account"))
    AccountService.updateAccountName(accountId, name)

    Redirect(routes.HomeController.index()).withSession {
      request.session + ("name" -> name)
    }.flashing("success" -> "Name changed.")
  }

  def newDefaultGame() = Action { implicit request =>
    Ok(views.html.gameplay("klondike"))
  }

  def newGame(variant: String) = Action { implicit request =>
    Ok(views.html.gameplay(variant))
  }

  def newGameWithSeed(variant: String, seed: Int) = Action { implicit request =>
    Ok(views.html.gameplay(variant, None, Some(seed)))
  }
}
