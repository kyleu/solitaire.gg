package services.history

import java.util.UUID

import models.database.queries.game.{ GameHistoryCardQueries, GameHistoryMoveQueries, GameHistoryQueries }
import org.joda.time.LocalDateTime
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

import scala.concurrent.Future

object GameHistoryService {
  def searchGames(q: String, orderBy: String, page: Int) = Database.query(GameHistoryQueries.count(q)).flatMap { count =>
    Database.query(GameHistoryQueries.search(q, getOrderClause(orderBy), Some(page))).map { list =>
      count -> list
    }
  }

  def getByUser(id: UUID, orderBy: String) = Database.query(GameHistoryQueries.GetGameHistoriesByUser(id, getOrderClause(orderBy)))

  def updateGameHistory(id: UUID, status: String, moves: Int, undos: Int, redos: Int, completed: Option[LocalDateTime]) = {
    Database.execute(GameHistoryQueries.UpdateGameHistory(id, status, moves, undos, redos, completed)).map(_ == 1)
  }

  def removeGameHistory(id: UUID) = for {
    moves <- Database.execute(GameHistoryMoveQueries.RemoveGameMovesByGame(id))
    cards <- Database.execute(GameHistoryCardQueries.RemoveGameCardsByGame(id))
    success <- Database.execute(GameHistoryQueries.removeById(Seq(id))).map(_ == 1)
  } yield (id, (success, cards, moves))

  def removeGameHistoriesByUser(userId: UUID) = getByUser(userId, "id").flatMap { games =>
    val futures = games.map(game => removeGameHistory(game.id))
    Future.sequence(futures)
  }

  private[this] def getOrderClause(orderBy: String) = orderBy match {
    case "game-id" => "id"
    case "created" => "created desc"
    case "completed" => "completed desc"
    case x => x
  }
}
