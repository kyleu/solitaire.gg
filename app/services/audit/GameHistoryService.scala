package services.audit

import java.util.UUID

import com.github.mauricio.async.db.Connection
import models.queries.history.GameHistoryQueries
import models.queries.user.UserQueries
import models.history.GameHistory
import org.joda.time.LocalDate
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

import scala.concurrent.Future

object GameHistoryService {
  def getGameHistory(id: UUID) = Database.query(GameHistoryQueries.getById(id))

  def getAll = Database.query(GameHistoryQueries.search("", "created", None))

  def searchGames(q: String, orderBy: String, page: Int) = Database.query(GameHistoryQueries.searchCount(q)).flatMap { count =>
    Database.query(GameHistoryQueries.search(q, getOrderClause(orderBy), Some(page))).map { list =>
      count -> list
    }
  }

  def getCountByUser(id: UUID) = Database.query(GameHistoryQueries.getGameHistoryCountForUser(id))

  def getWins(d: LocalDate) = Database.query(GameHistoryQueries.GetGameHistoriesByDayAndStatus(d, "win")).flatMap { histories =>
    Future.sequence(histories.map { h =>
      Database.query(UserQueries.getById(h.player)).map(u => (h, u.getOrElse(throw new IllegalStateException())))
    })
  }

  def insert(gh: GameHistory) = Database.execute(GameHistoryQueries.insert(gh)).map(_ => true)

  def setCounts(id: UUID, moves: Int, undos: Int, redos: Int, score: Int) = {
    Database.execute(GameHistoryQueries.SetCounts(id, moves, undos, redos, score)).map(_ == 1)
  }

  def removeGameHistory(id: UUID, conn: Option[Connection]) = Database.execute(GameHistoryQueries.removeById(id), conn).map(_ == 1).map { success =>
    (id, success)
  }

  def removeGameHistoriesByUser(userId: UUID) = Database.query(GameHistoryQueries.GetGameHistoryIdsForUser(userId)).flatMap { gameIds =>
    Future.sequence(gameIds.map(id => removeGameHistory(id, None)))
  }

  private[this] def getOrderClause(orderBy: String) = orderBy match {
    case "game-id" => "id"
    case "created" => "created desc"
    case "completed" => "completed desc"
    case x => x
  }
}
