package controllers

import java.util.UUID

import controllers.BaseController.AuthenticatedAction
import services.AccountService

object AccountController extends BaseController {
  def accountList(q: String) = AuthenticatedAction { implicit request =>
    val accounts = AccountService.searchAccounts(q)
    Ok(views.html.admin.accountList(q, accounts))
  }

  def removeAccount(id: UUID) = AuthenticatedAction { implicit request =>
    AccountService.removeAccount(id)
    Redirect(routes.AccountController.accountList(""))
  }
}
