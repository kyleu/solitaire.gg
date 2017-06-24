package services.audit.data

import java.util.UUID

import models.history.GameHistory
import models.queries.history.GameHistoryQueries
import models.rules.GameRulesSet
import models.user.User
import org.joda.time.LocalDateTime
import utils.FutureUtils.defaultContext
import play.api.libs.json.{JsNumber, JsValue}
import services.database.Database
import utils.DateUtils

import scala.concurrent.Future

object GameHistoryInsert {
  def test(id: UUID, data: JsValue): String = parse(id, data) match {
    case Some(_) => "Start: Parsed"
    case None => "Start: Skipped"
  }

  def insert(id: UUID, data: JsValue): Future[String] = parse(id, data) match {
    case Some((deviceId, userId, gameId, seed, rules, cardCount, occurred)) => queries(deviceId, userId, gameId, seed, rules, cardCount, occurred)
    case _ => Future.successful("Start: Skipped")
  }

  private[this] def parse(id: UUID, data: JsValue) = {
    val o = data.as[Map[String, JsValue]]
    o.get("gameId").map(_.as[UUID]).map { gameId =>
      val deviceId = o.get("deviceId").map(_.as[UUID]).getOrElse(User.defaultId)
      val userId = o.get("userId").map(_.as[UUID]).getOrElse(deviceId)

      val seed = o.getOrElse("seed", JsNumber(-1)).as[Int]
      val rules = o("rules").as[String]
      val cardCount = o.get("cards").map(_.as[Int]).getOrElse(GameRulesSet.allByIdWithAliases.get(rules).map(_.deckOptions.cardCount).getOrElse(0))
      val occurred = AnalyticsDataInsert.getDate(o).getOrElse(DateUtils.fromMillis(0))
      (deviceId, userId, gameId, seed, rules, cardCount, occurred)
    }
  }

  private[this] def queries(deviceId: UUID, userId: UUID, gameId: UUID, seed: Int, rules: String, cardCount: Int, occurred: LocalDateTime) = {
    val gameQuery = Database.query(GameHistoryQueries.getById(gameId)).flatMap {
      case Some(_) => Database.execute(GameHistoryQueries.removeById(gameId))
      case None => Future.successful(0)
    }

    AnalyticsDataInsert.confirmUser(userId, occurred).flatMap { _ =>
      gameQuery.flatMap { _ =>
        val q = GameHistoryQueries.insert(GameHistory(
          id = gameId,
          rules = rules,
          seed = seed,
          status = GameHistory.Status.Started,
          player = userId,
          cards = cardCount,
          created = occurred
        ))
        Database.execute(q).map(_ => s"Start: Inserted")
      }
    }
  }
}
