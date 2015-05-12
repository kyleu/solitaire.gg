package services.game

import java.util.UUID

import models.database.queries.GameHistoryQueries._
import org.joda.time.LocalDateTime
import services.database.Database

import play.api.libs.concurrent.Execution.Implicits.defaultContext

object GameHistoryService {
  def startGame(id: UUID, seed: Int, rules: String, status: String, accounts: Seq[UUID], created: LocalDateTime) = {
    Database.execute(CreateGameHistory(id, seed, rules, status, accounts, created)).map(_ == 1)
  }

  def searchGames(q: String, orderBy: String) = Database.query(SearchGameHistories(q, orderBy))

  def getByAccount(id: UUID, sortBy: String) = Database.query(GetGameHistoriesByAccount(id, sortBy))

  def updateGameHistory(id: UUID, status: String, moves: Int, undos: Int, redos: Int, completed: Option[LocalDateTime]) = {
    Database.execute(UpdateGameHistory(id, status, moves, undos, redos, completed)).map(_ == 1)
  }

  def removeGameHistory(id: UUID) = {
    Database.execute(RemoveGameHistory(id)).map(_ == 1)
  }

  private[this] def getOrderClause(orderBy: String) = orderBy match {
    case "game-id" => "id"
    case "created" => "created desc"
    case "completed" => "completed desc"
    case x => x
  }
}
