package services.history

import java.sql.Connection

import models.history.{GameHistory, GameStatistics}
import models.queries.history.GameStatisticsQueries
import services.database.Database
import util.FutureUtils.defaultContext

import scala.concurrent.Future

object GameStatisticsService {
  def gameStarted(rules: String) = Database.execute(GameStatisticsQueries.Increment(rules, "played", 1)).flatMap {
    case rows if rows == 0 => getStatistics(rules).flatMap { _ =>
      Database.execute(GameStatisticsQueries.Increment(rules, "played", 1)).map(_ => ())
    }
    case _ => Future.successful(())
  }

  def registerGame(gh: GameHistory): Future[Unit] = {
    gh.completed.getOrElse(throw new IllegalStateException(s"Game [${gh.id}] has not been completed."))
    Database.execute(GameStatisticsQueries.OnFinish(gh)).flatMap {
      case affected if affected == 0 => getStatistics(gh.rules).flatMap { _ =>
        registerGame(gh)
      }
      case _ => Future.successful(Unit)
    }
  }

  def getStatistics(rules: String) = Database.query(GameStatisticsQueries.getById(rules)).flatMap {
    case Some(stats) => Future.successful(stats)
    case None =>
      val stats = GameStatistics.empty(rules)
      Database.execute(GameStatisticsQueries.insert(stats)).map(_ => stats)
  }

  def removeStatisticsForUser(rules: String, conn: Option[Connection]) = {
    Database.execute(GameStatisticsQueries.removeById(rules), conn)
  }
}
