package controllers.admin

import java.util.UUID

import controllers.BaseController
import controllers.BaseController.AuthenticatedAction
import services.account.AccountService
import services.game.GameHistoryService

object AccountController extends BaseController {
  def accountList(q: String, sortBy: String) = AuthenticatedAction { implicit request =>
    requireAdmin {
      val accounts = AccountService.searchAccounts(q)
      Ok(views.html.admin.accountList(q, sortBy, accounts))
    }
  }

  def accountDetail(id: UUID, sortBy: String) = AuthenticatedAction { implicit request =>
    requireAdmin {
      AccountService.getAccount(id) match {
        case Some(account) =>
          val games = GameHistoryService.getByAccount(id, sortBy)
          Ok(views.html.admin.accountDetail(account, games, sortBy))
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
