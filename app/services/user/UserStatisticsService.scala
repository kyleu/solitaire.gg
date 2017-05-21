package services.user

import java.util.UUID

import com.github.mauricio.async.db.Connection
import models.database.Statement
import models.history.GameHistory
import models.queries.user.{UserQueries, UserStatisticsQueries}
import models.user.UserStatistics
import org.joda.time.LocalDateTime
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import utils.DateUtils

import scala.concurrent.Future

object UserStatisticsService {
  def gameStarted(id: UUID, rules: String, seed: Int, player: UUID) = Database.execute(UserStatisticsQueries.Increment(player, "played", 1)).flatMap {
    case rows if rows == 0 => UserStatisticsService.getStatistics(player).flatMap { _ =>
      Database.execute(UserStatisticsQueries.Increment(player, "played", 1)).map(_ => ())
    }
    case _ => Future.successful(())
  }

  def registerGame(game: GameHistory) = {
    val completed = game.completed.getOrElse(throw new IllegalStateException(s"Game [${game.id}] has not been completed."))
    registerHistory(game.id, game.isWon, game.duration, game.moves, game.undos, game.redos, completed, game.player)
  }

  def registerHistory(
    gameId: UUID, isWin: Boolean, duration: Long, moves: Int, undos: Int, redos: Int, completed: LocalDateTime, player: UUID
  ): Future[UserStatistics] = {
    val update = new Statement {
      override def sql = UserStatisticsQueries.updateSql(isWin)
      override def values = Seq[Any](duration, moves, undos, redos, completed, player)
    }

    Database.execute(update).flatMap {
      case affected if affected == 1 => Future.successful(Unit)
      case _ => UserStatisticsService.getStatistics(player).flatMap { _ =>
        registerHistory(gameId, isWin, duration, moves, undos, redos, completed, player)
      }
    }.flatMap(_ => getStatistics(player))
  }

  def getStatistics(user: UUID) = Database.query(UserStatisticsQueries.getById(user)).flatMap {
    case Some(stats) => Future.successful(stats)
    case None => Database.query(UserQueries.GetCreatedDate(user)).flatMap {
      case Some(joined) =>
        val stats = UserStatistics(user, DateUtils.toMillis(joined), UserStatistics.Games.empty)
        Database.execute(UserStatisticsQueries.insert(stats)).map { _ =>
          stats
        }
      case None => throw new IllegalStateException()
    }
  }

  def removeStatisticsForUser(userId: UUID, conn: Option[Connection]) = {
    Database.execute(UserStatisticsQueries.removeById(userId), conn)
  }
}
