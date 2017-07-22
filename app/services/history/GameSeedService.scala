package services.history

import java.util.UUID

import models.history.{GameHistory, GameSeed}
import models.queries.BaseQueries
import models.queries.history.GameSeedQueries
import util.FutureUtils.defaultContext
import services.database.Database
import util.Logging

import scala.concurrent.Future

object GameSeedService extends Logging {
  def getGameSeed(rules: String, seed: Int) = Database.query(GameSeedQueries.getByKey(rules, seed))

  def getAll(limit: Option[Int], offset: Option[Int]) = Database.query(GameSeedQueries.getAll(limit, offset))

  def search(q: String, limit: Option[Int], offset: Option[Int]) = Database.query(GameSeedQueries.search(q, "id desc", limit, offset))

  def getCountByUser(id: UUID) = Database.query(GameSeedQueries.getCountForUser(id))

  def insert(gs: GameSeed) = Database.execute(GameSeedQueries.insert(gs)).map(_ => true)

  def search(q: String, orderBy: String, page: Int) = Database.query(GameSeedQueries.searchCount(q)).flatMap { count =>
    Database.query(GameSeedQueries.search(q, getOrderClause(orderBy), Some(BaseQueries.pageSize), Some(page * BaseQueries.pageSize))).map { list =>
      count -> list
    }
  }

  def onStart(rules: String, seed: Int) = Database.query(GameSeedQueries.getByKey(rules, seed)).flatMap {
    case Some(gs) => Database.execute(GameSeedQueries.IncrementGames(rules, seed, 1)).map(_ == 1)
    case None => Database.execute(GameSeedQueries.insert(GameSeed(rules = rules, seed = seed))).flatMap { _ =>
      Database.execute(GameSeedQueries.IncrementGames(rules, seed, 1)).map(_ == 1)
    }
    case x => throw new IllegalStateException(s"Invalid return value [$x].")
  }

  def onComplete(gh: GameHistory) = Database.execute(GameSeedQueries.OnComplete(gh)).flatMap {
    case 1 => Future.successful(true)
    case 0 => Database.execute(GameSeedQueries.insert(GameSeed(rules = gh.rules, seed = gh.seed))).flatMap { _ =>
      Database.execute(GameSeedQueries.OnComplete(gh)).map(_ == 1)
    }
    case x => throw new IllegalStateException(s"Invalid return value [$x].")
  }

  def remove(rules: String, seed: Int) = Database.execute(GameSeedQueries.removeByKey(rules, seed)).map(_ == 1)

  private[this] def getOrderClause(orderBy: String) = orderBy match {
    case "completed" => "completed desc"
    case x => x
  }
}
