package services.history

import java.util.UUID

import models.history.{GameHistory, GameSeed}
import models.queries.history.GameSeedQueries
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import utils.Logging

import scala.concurrent.Future

object GameSeedService extends Logging {
  def getGameSeed(rules: String, seed: Int) = Database.query(GameSeedQueries.getByKey(rules, seed))

  def getAll = Database.query(GameSeedQueries.search("", "completed desc"))

  def getCountByUser(id: UUID) = Database.query(GameSeedQueries.getCountForUser(id))

  def insert(gs: GameSeed) = Database.execute(GameSeedQueries.insert(gs)).map(_ => true)

  def search(q: String, orderBy: String, page: Int) = Database.query(GameSeedQueries.searchCount(q)).flatMap { count =>
    Database.query(GameSeedQueries.search(q, getOrderClause(orderBy), Some(page))).map { list =>
      count -> list
    }
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
