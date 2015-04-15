package controllers.admin

import java.util.UUID

import controllers.BaseController
import controllers.BaseController.AuthenticatedAction
import services.AccountService

object AccountController extends BaseController {
  def accountList(q: String) = AuthenticatedAction { implicit request =>
    requireAdmin {
      val accounts = AccountService.searchAccounts(q)
      Ok(views.html.admin.accountList(q, accounts))
    }
  }

  def removeAccount(id: UUID) = AuthenticatedAction { implicit request =>
    requireAdmin {
      AccountService.removeAccount(id)
      Redirect(controllers.admin.routes.AccountController.accountList(""))
    }
  }
}
