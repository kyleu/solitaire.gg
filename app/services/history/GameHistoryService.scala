package services.history

import java.util.UUID

import com.github.mauricio.async.db.Connection
import models.database.queries.history.{ GameHistoryQueries, GameHistoryMoveQueries, GameHistoryCardQueries }
import org.joda.time.LocalDateTime
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

import scala.concurrent.Future

object GameHistoryService {
  def searchGames(q: String, orderBy: String, page: Int) = for {
    count <- Database.query(GameHistoryQueries.searchCount(q))
    list <- Database.query(GameHistoryQueries.search(q, getOrderClause(orderBy), Some(page)))
  } yield count -> list

  def getCountByUser(id: UUID) = Database.query(GameHistoryQueries.getGameHistoryCountForUser(id))

  def updateGameHistory(id: UUID, status: String, moves: Int, undos: Int, redos: Int, completed: Option[LocalDateTime]) = {
    Database.execute(GameHistoryQueries.UpdateGameHistory(id, status, moves, undos, redos, completed)).map(_ == 1)
  }

  def removeGameHistory(id: UUID, conn: Option[Connection]) = for {
    moves <- Database.execute(GameHistoryMoveQueries.RemoveGameMovesByGame(id), conn)
    cards <- Database.execute(GameHistoryCardQueries.RemoveGameCardsByGame(id), conn)
    success <- Database.execute(GameHistoryQueries.removeById(Seq(id)), conn).map(_ == 1)
  } yield (id, (success, cards, moves))

  def removeGameHistoriesByUser(userId: UUID, conn: Option[Connection]) = {
    Database.query(GameHistoryQueries.GetGameHistoryIdsForUser(userId)).flatMap { gameIds =>
      val futures = gameIds.map(id => removeGameHistory(id, conn))
      Future.sequence(futures)
    }
  }

  private[this] def getOrderClause(orderBy: String) = orderBy match {
    case "game-id" => "id"
    case "created" => "created desc"
    case "completed" => "completed desc"
    case x => x
  }
}
