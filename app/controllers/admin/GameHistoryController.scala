package controllers.admin

import java.util.UUID

import controllers.BaseController
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.history.GameHistoryService
import services.user.AuthenticationEnvironment

import scala.concurrent.Future

@javax.inject.Singleton
class GameHistoryController @javax.inject.Inject() (
    override val messagesApi: MessagesApi,
    override val env: AuthenticationEnvironment
) extends BaseController {
  def gameList(q: String, sortBy: String, page: Int) = withAdminSession { implicit request =>
    GameHistoryService.searchGames(q, sortBy, page).map { games =>
      Ok(views.html.admin.game.gameList(q, sortBy, games._1, page, games._2))
    }
  }

  def gameDetail(id: UUID) = withAdminSession { implicit request =>
    GameHistoryService.getGameHistory(id).flatMap {
      case Some(game) => Future.successful(Ok(views.html.admin.game.gameDetail(game)))
      case None => Future.successful(NotFound(s"Game [$id] not found."))
    }
  }

  def removeGame(id: UUID) = withAdminSession { implicit request =>
    GameHistoryService.removeGameHistory(id, None).map { ok =>
      val msg = s"Game [$id] removed, along with their ${ok._2._2} cards and ${ok._2._3} moves."
      Redirect(controllers.admin.routes.GameHistoryController.gameList("")).flashing("success" -> msg)
    }
  }
}
