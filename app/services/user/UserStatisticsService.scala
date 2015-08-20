package services.user

import java.util.UUID

import models.database.Statement
import models.database.queries.user.{ UserQueries, UserStatisticsQueries }
import models.history.GameHistory
import models.user.UserStatistics
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

import scala.concurrent.Future

object UserStatisticsService {
  def registerGame(game: GameHistory): Future[Unit.type] = {
    if (!game.isCompleted) {
      throw new IllegalStateException(s"Game [${game.id}] is not completed.")
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
