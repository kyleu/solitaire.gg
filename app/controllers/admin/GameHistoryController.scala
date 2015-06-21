package controllers.admin

import controllers.BaseController
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.history.GameHistoryService

@javax.inject.Singleton
class GameHistoryController @javax.inject.Inject() (override val messagesApi: MessagesApi) extends BaseController {
  def gameList(q: String, sortBy: String, page: Int) = withAdminSession { implicit request =>
    GameHistoryService.searchGames(q, sortBy, page).map { games =>
      Ok(views.html.admin.game.gameList(q, sortBy, games._1, page, games._2))
    }
  }
}
