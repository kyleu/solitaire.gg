package controllers.admin

import java.util.UUID

import controllers.BaseController
import utils.Application
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.history.GameHistoryService

import scala.concurrent.Future

@javax.inject.Singleton
class GameHistoryController @javax.inject.Inject() (override val app: Application) extends BaseController {
  def list(q: String, sortBy: String, page: Int) = withAdminSession("list") { implicit request =>
    GameHistoryService.searchGames(q, sortBy, page).map { games =>
      Ok(views.html.admin.game.list(q, sortBy, games._1, page, games._2))
    }
  }

  def detail(id: UUID) = withAdminSession("detail") { implicit request =>
    GameHistoryService.getGameHistory(id).flatMap {
      case Some(game) => Future.successful(Ok(views.html.admin.game.detail(game)))
      case None => Future.successful(NotFound(s"Game [$id] not found."))
    }
  }

  def remove(id: UUID) = withAdminSession("remove") { implicit request =>
    GameHistoryService.removeGameHistory(id, None).map { ok =>
      val msg = s"Game [$id] removed."
      Redirect(controllers.admin.routes.GameHistoryController.list()).flashing("success" -> msg)
    }
  }
}
