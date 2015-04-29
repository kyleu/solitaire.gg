package services

import java.util.UUID

import models.queries.GameHistoryQueries._
import org.joda.time.LocalDateTime
import services.database.DatabaseConnection

object GameHistoryService {
  def startGame(id: UUID, seed: Int, rules: String, status: String, accounts: Seq[UUID], created: LocalDateTime) = {
    DatabaseConnection.execute(CreateGameHistory(id, seed, rules, status, accounts, created))
  }

  def searchGames(q: String, orderBy: String) = DatabaseConnection.query(SearchGameHistories(q, orderBy))

  def getByAccount(id: UUID, sortBy: String) = DatabaseConnection.query(GetGameHistoriesByAccount(id, sortBy))

  def updateGameHistory(id: UUID, status: String, moves: Int, undos: Int, redos: Int, completed: Option[LocalDateTime]) = DatabaseConnection.transaction {
    DatabaseConnection.execute(UpdateGameHistory(id, status, moves, undos, redos, completed)) == 1
  }

  def removeGameHistory(id: UUID) = DatabaseConnection.transaction {
    DatabaseConnection.execute(RemoveGameHistory(id)) == 1
  }

  private[this] def getOrderClause(orderBy: String) = orderBy match {
    case "game-id" => "id"
    case "created" => "created desc"
    case "completed" => "completed desc"
    case x => x
  }
}
