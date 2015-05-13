package controllers.admin

import java.util.UUID

import controllers.BaseController
import models.database.queries.AccountQueries.{GetAccount, RemoveAccount, SearchAccounts}
import models.user.{Role, WithRole}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import services.game.GameHistoryService

import scala.concurrent.Future

object AccountController extends BaseController {
  def accountList(q: String, sortBy: String) = SecuredAction(WithRole(Role.Admin)).async { implicit request =>
    Database.query(SearchAccounts(q)).map { accounts =>
      Ok(views.html.admin.accountList(q, sortBy, accounts))
    }
  }

  def accountDetail(id: UUID, sortBy: String) = SecuredAction(WithRole(Role.Admin)).async { implicit request =>
    Database.query(GetAccount(id)).flatMap {
      case Some(account) => GameHistoryService.getByAccount(id, sortBy).map { games =>
        Ok(views.html.admin.accountDetail(account, games, sortBy))
      }
      case None => Future.successful(NotFound("Account [" + id + "] not found."))
    }
  }

  def removeAccount(id: UUID) = SecuredAction(WithRole(Role.Admin)).async { implicit request =>
    Database.execute(RemoveAccount(id)).map { i =>
      Redirect(controllers.admin.routes.AccountController.accountList(""))
    }
  }
}
