package services.audit.data

import java.util.UUID

import models.history.GameHistory
import models.queries.history.GameHistoryQueries
import models.user.User
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsArray, JsObject, JsValue}
import services.database.Database
import services.user.UserStatisticsService
import utils.DateUtils

import scala.concurrent.Future

object GameResignedHistoryInsert {
  def test(id: UUID, data: JsValue): String = {
    parse(id, data)
    "Resigned: Parsed."
  }

  def insert(id: UUID, data: JsValue): Future[String] = parse(id, data) match {
    case Some(r) => queries(r._3)
    case _ => Future.successful("Resigned: Skipped")
  }

  private[this] def parse(id: UUID, data: JsValue) = {
    val o = data.as[Map[String, JsValue]]
    val deviceId = o.get("deviceId").map(_.as[UUID]).getOrElse(User.defaultId)
    val userId = o.get("userId").map(_.as[UUID]).getOrElse(deviceId)
    val occurred = AnalyticsDataInsert.getDate(o).getOrElse(DateUtils.fromMillis(0))
    val msgObj = o.getOrElse("message", JsObject(Seq.empty)) match {
      case a: JsArray if a.value.size == 2 => a.value(1).as[JsObject]
      case o: JsObject => o
      case x => throw new IllegalStateException(s"Invalid message: [$x].")
    }
    msgObj.value.get("id").map(_.as[UUID]).map { gameId =>
      val resultObj = msgObj.value.get("result").map(_.as[Map[String, JsValue]]).getOrElse(Map.empty)
      val duration = resultObj.get("durationSeconds").map(x => (x.as[Double] * 1000).toInt).getOrElse(0)
      val gh = GameHistory(
        id = gameId,
        rules = "",
        seed = 0,
        status = GameHistory.Status.Resigned,
        userId,
        0,
        moves = resultObj.get("moves").map(_.as[Int]).getOrElse(0),
        undos = resultObj.get("undos").map(_.as[Int]).getOrElse(0),
        redos = resultObj.get("redos").map(_.as[Int]).getOrElse(0),
        score = resultObj.get("score").map(_.as[Int]).getOrElse(0),
        created = occurred.minusMillis(duration),
        firstMove = Some(occurred.minusMillis(duration)),
        completed = Some(occurred)
      )
      (id, deviceId, gh)
    }
  }

  private[this] def queries(gh: GameHistory): Future[String] = {
    UserStatisticsService.registerGame(gh).flatMap { _ =>
      Database.execute(GameHistoryQueries.OnComplete(gh)).map(_ => "Resigned: Inserted")
    }
  }
}
