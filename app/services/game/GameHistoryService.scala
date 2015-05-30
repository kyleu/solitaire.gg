package services.game

import java.util.UUID
import models.database.queries.game.GameHistoryQueries
import org.joda.time.LocalDateTime
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

object GameHistoryService {
  def searchGames(q: String, orderBy: String, page: Int) = Database.query(GameHistoryQueries.CountQuery(q)).flatMap { count =>
    Database.query(GameHistoryQueries.SearchQuery(q, getOrderClause(orderBy), Some(page))).map { list =>
      count -> list
    }
  }

  def getByUser(id: UUID, orderBy: String) = Database.query(GameHistoryQueries.GetGameHistoriesByUser(id, getOrderClause(orderBy)))

  def updateGameHistory(id: UUID, status: String, moves: Int, undos: Int, redos: Int, completed: Option[LocalDateTime]) = {
    Database.execute(GameHistoryQueries.UpdateGameHistory(id, status, moves, undos, redos, completed)).map(_ == 1)
  }

  def removeGameHistory(id: UUID) = {
    Database.execute(GameHistoryQueries.RemoveById(Seq(id))).map(_ == 1)
  }

  private[this] def getOrderClause(orderBy: String) = orderBy match {
    case "game-id" => "id"
    case "created" => "created desc"
    case "completed" => "completed desc"
    case x => x
  }
}
