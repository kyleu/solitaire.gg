package services.history

import java.util.UUID

import com.github.mauricio.async.db.Connection
import models.queries.history.{ GameHistoryQueries, GameHistoryMoveQueries, GameHistoryCardQueries }
import models.queries.user.UserQueries
import models.history.GameHistory
import org.joda.time.{ LocalDate, LocalDateTime }
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

import scala.concurrent.Future

object GameHistoryService {
  def getGameHistory(id: UUID) = Database.query(GameHistoryQueries.getById(id))

  def getAll = Database.query(GameHistoryQueries.search("", "created", None))

  def searchGames(q: String, orderBy: String, page: Int) = for {
    count <- Database.query(GameHistoryQueries.searchCount(q))
    list <- Database.query(GameHistoryQueries.search(q, getOrderClause(orderBy), Some(page)))
  } yield count -> list

  def getCountByUser(id: UUID) = Database.query(GameHistoryQueries.getGameHistoryCountForUser(id))

  def getWins(d: LocalDate) = Database.query(GameHistoryQueries.GetGameHistoriesByDayAndStatus(d, "win")).flatMap { histories =>
    Future.sequence(histories.map { h =>
      Database.query(UserQueries.getById(h.player)).map(u => (h, u.getOrElse(throw new IllegalStateException())))
    })
  }

  def setLoggedTime(id: UUID, dt: LocalDateTime) = Database.execute(GameHistoryQueries.UpdateGameHistoryLoggedTime(id, dt))

  def insert(gh: GameHistory) = Database.execute(GameHistoryQueries.insert(gh)).map(ok => true)

  def updateGameHistory(gh: GameHistory) = {
    Database.execute(GameHistoryQueries.UpdateGameHistory(gh.id, gh.status, gh.moves, gh.undos, gh.redos, gh.completed, gh.logged)).map(_ == 1)
  }

  def removeGameHistory(id: UUID, conn: Option[Connection]) = for {
    moves <- Database.execute(GameHistoryMoveQueries.RemoveGameMovesByGame(id), conn)
    cards <- Database.execute(GameHistoryCardQueries.RemoveGameCardsByGame(id), conn)
    success <- Database.execute(GameHistoryQueries.removeById(Seq(id)), conn).map(_ == 1)
  } yield (id, (success, cards, moves))

  def removeGameHistoriesByUser(userId: UUID) = {
    Database.query(GameHistoryQueries.GetGameHistoryIdsForUser(userId)).flatMap { gameIds =>
      Future.sequence(gameIds.map(id => removeGameHistory(id, None)))
    }
  }

  private[this] def getOrderClause(orderBy: String) = orderBy match {
    case "game-id" => "id"
    case "created" => "created desc"
    case "completed" => "completed desc"
    case x => x
  }
}
