package services.user

import java.util.UUID

import models.database.Statement
import models.queries.user.{ UserQueries, UserStatisticsQueries }
import models.history.GameHistory
import models.user.UserStatistics
import org.joda.time.LocalDateTime
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import services.history.GameHistoryService

import scala.concurrent.Future

object UserStatisticsService {
  def registerGame(game: GameHistory): Future[Unit.type] = {
    if (!game.isCompleted) {
      throw new IllegalStateException(s"Game [${game.id}] is not completed.")
    }
    if (game.logged.isDefined) {
      throw new IllegalStateException(s"Game [${game.id}] is already logged.")
    }

    val update = new Statement {
      override def sql = UserStatisticsQueries.updateSql(game.isWin)
      override def values = Seq[Any](
        game.duration, game.moves, game.undos, game.redos,
        game.completed.getOrElse(throw new IllegalStateException()),
        game.player
      )
    }

    Database.execute(update).flatMap {
      case affected if affected == 1 => Future.successful(Unit)
      case _ => UserStatisticsService.getStatistics(game.player).flatMap { stats =>
        registerGame(game)
      }
    }.flatMap { _ =>
      GameHistoryService.setLoggedTime(game.id, new LocalDateTime()).map(x => Unit)
    }
  }

  def getStatistics(user: UUID) = Database.query(UserStatisticsQueries.getById(user)).flatMap {
    case Some(stats) => Future.successful(stats)
    case None => Database.query(UserQueries.GetCreatedDate(user)).flatMap {
      case Some(joined) =>
        val stats = UserStatistics(user, joined.toLocalDate, UserStatistics.Games())
        Database.execute(UserStatisticsQueries.insert(stats)).map { _ =>
          stats
        }
      case None => throw new IllegalStateException()
    }
  }
}
