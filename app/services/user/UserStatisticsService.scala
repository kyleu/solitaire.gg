package services.user

import java.sql.Connection
import java.util.UUID

import models.history.GameHistory
import models.queries.user.{UserQueries, UserStatisticsQueries}
import models.user.UserStatistics
import util.FutureUtils.defaultContext
import services.database.Database
import util.DateUtils

import scala.concurrent.Future

object UserStatisticsService {
  def gameStarted(player: UUID) = Database.execute(UserStatisticsQueries.Increment(player, "played", 1)).flatMap {
    case rows if rows == 0 => getStatistics(player).flatMap(_ => Database.execute(UserStatisticsQueries.Increment(player, "played", 1)).map(_ => ()))
    case _ => Future.successful(())
  }

  def registerGame(gh: GameHistory): Future[Unit] = {
    gh.completed.getOrElse(throw new IllegalStateException(s"Game [${gh.id}] has not been completed."))
    Database.execute(UserStatisticsQueries.OnFinish(gh)).flatMap {
      case affected if affected == 0 => getStatistics(gh.player).flatMap(_ => registerGame(gh))
      case _ => Future.successful(Unit)
    }
  }

  def getStatistics(user: UUID) = Database.query(UserStatisticsQueries.getById(user)).flatMap {
    case Some(stats) => Future.successful(stats)
    case None => Database.query(UserQueries.GetCreatedDate(user)).flatMap {
      case Some(joined) =>
        val stats = UserStatistics(user, DateUtils.toMillis(joined), UserStatistics.Games.empty)
        Database.execute(UserStatisticsQueries.insert(stats)).map(_ => stats)
      case None => throw new IllegalStateException()
    }
  }

  def removeStatisticsForUser(userId: UUID, conn: Option[Connection]) = Database.execute(UserStatisticsQueries.removeById(userId), conn)
}
