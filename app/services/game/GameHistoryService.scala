package services.game

import java.util.UUID

import models.database.queries.GameHistoryQueries
import org.joda.time.LocalDateTime
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

object GameHistoryService {
  def startGame(id: UUID, seed: Int, rules: String, status: String, userId: UUID, created: LocalDateTime) = {
    Database.execute(GameHistoryQueries.CreateGameHistory(id, seed, rules, status, userId, created)).map(_ == 1)
  }

  def searchGames(q: String, orderBy: String) = Database.query(GameHistoryQueries.SearchGameHistories(q, orderBy))

  def getByUser(id: UUID, sortBy: String) = Database.query(GameHistoryQueries.GetGameHistoriesByUser(id, sortBy))

  def updateGameHistory(id: UUID, status: String, moves: Int, undos: Int, redos: Int, completed: Option[LocalDateTime]) = {
    Database.execute(GameHistoryQueries.UpdateGameHistory(id, status, moves, undos, redos, completed)).map(_ == 1)
  }

  def removeGameHistory(id: UUID) = {
    Database.execute(GameHistoryQueries.RemoveGameHistory(id)).map(_ == 1)
  }

  private[this] def getOrderClause(orderBy: String) = orderBy match {
    case "game-id" => "id"
    case "created" => "created desc"
    case "completed" => "completed desc"
    case x => x
  }
}
