package controllers.admin

import java.util.UUID

import controllers.BaseController
import controllers.BaseController.AuthenticatedAction
import services.{ GameHistoryService, AccountService }

object AccountController extends BaseController {
  def accountList(q: String) = AuthenticatedAction { implicit request =>
    requireAdmin {
      val accounts = AccountService.searchAccounts(q)
      Ok(views.html.admin.accountList(q, accounts))
    }
  }

  def accountDetail(id: UUID) = AuthenticatedAction { implicit request =>
    requireAdmin {
      AccountService.getAccount(id) match {
        case Some(account) =>
          val games = GameHistoryService.getByAccount(id)
          Ok(views.html.admin.accountDetail(account, games))
        case None => NotFound("Account [" + id + "] not found.")
      }
    }
  }

  def removeAccount(id: UUID) = AuthenticatedAction { implicit request =>
    requireAdmin {
      AccountService.removeAccount(id)
      Redirect(controllers.admin.routes.AccountController.accountList(""))
    }
  }
}
