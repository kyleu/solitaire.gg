package services

import java.util.UUID

import models.queries.GameHistoryQueries._
import org.joda.time.LocalDateTime
import services.database.DatabaseConnection

object GameHistoryService {
  def startGame(id: UUID, seed: Int, variant: String, status: String, accounts: Seq[UUID], created: LocalDateTime) = {
    DatabaseConnection.execute(CreateGameHistory(id, seed, variant, status, accounts, created))
  }

  def searchGames(q: String) = DatabaseConnection.query(SearchGameHistories(q))

  def getByAccount(id: UUID) = DatabaseConnection.query(GetGameHistoriesByAccount(id))

  def updateGameHistory(id: UUID, status: String, moves: Int, undos: Int, redos: Int, completed: Option[LocalDateTime]) = DatabaseConnection.transaction {
    DatabaseConnection.execute(UpdateGameHistory(id, status, moves, undos, redos, completed)) == 1
  }

  def removeGameHistory(id: UUID) = DatabaseConnection.transaction {
    DatabaseConnection.execute(RemoveGameHistory(id)) == 1
  }
}
