package controllers.admin

import java.util.UUID

import controllers.BaseController
import controllers.BaseController.AuthenticatedAction
import services.account.AccountService
import services.game.GameHistoryService

import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

object AccountController extends BaseController {
  def accountList(q: String, sortBy: String) = AuthenticatedAction.async { implicit request =>
    AccountService.searchAccounts(q).map { accounts =>
      Ok(views.html.admin.accountList(q, sortBy, accounts))
    }
  }

  def accountDetail(id: UUID, sortBy: String) = AuthenticatedAction.async { implicit request =>
    AccountService.getAccount(id).flatMap {
      case Some(account) =>
        GameHistoryService.getByAccount(id, sortBy).map { games =>
          Ok(views.html.admin.accountDetail(account, games, sortBy))
        }
      case None => Future.successful(NotFound("Account [" + id + "] not found."))
    }
  }

  def removeAccount(id: UUID) = AuthenticatedAction { implicit request =>
    AccountService.removeAccount(id)
    Redirect(controllers.admin.routes.AccountController.accountList(""))
  }
}
