package services

import java.util.UUID

import models.queries.GameHistoryQueries.{ UpdateGameHistory, RemoveGameHistory, SearchGameHistories, CreateGameHistory }
import org.joda.time.LocalDateTime
import services.database.DatabaseConnection

object GameHistoryService {
  def startGame(id: UUID, seed: Int, variant: String, status: String, accounts: Seq[UUID]) = {
    DatabaseConnection.execute(CreateGameHistory(id, seed, variant, status, accounts))
  }

  def searchGames(q: String) = DatabaseConnection.query(SearchGameHistories(q))

  def updateGameHistory(id: UUID, status: String, moves: Int, undos: Int, redos: Int, completed: Option[LocalDateTime]) = DatabaseConnection.transaction {
    DatabaseConnection.execute(UpdateGameHistory(id, status, moves, undos, redos, completed)) == 1
  }

  def removeGameHistory(id: UUID) = DatabaseConnection.transaction {
    DatabaseConnection.execute(RemoveGameHistory(id)) == 1
  }
}
