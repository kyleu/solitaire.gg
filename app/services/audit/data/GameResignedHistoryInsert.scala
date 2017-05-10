package services.audit.data

import java.util.UUID

import models.queries.history.GameHistoryQueries
import models.user.User
import org.joda.time.LocalDateTime
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
    case Some(r) => queries(r)
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
      val moves = resultObj.get("moves").map(_.as[Int]).getOrElse(0)
      val undos = resultObj.get("undos").map(_.as[Int]).getOrElse(0)
      val redos = resultObj.get("redos").map(_.as[Int]).getOrElse(0)
      val score = resultObj.get("score").map(_.as[Int]).getOrElse(0)
      val duration = resultObj.get("durationSeconds").map(x => (x.as[Double] * 1000).toLong).getOrElse(0L)
      (id, deviceId, userId, gameId, moves, undos, redos, score, duration, occurred)
    }
  }

  private[this] def queries(record: (UUID, UUID, UUID, UUID, Int, Int, Int, Int, Long, LocalDateTime)): Future[String] = {
    val (_, _, userId, gameId, moves, undos, redos, score, duration, occurred) = record
    val q = UserStatisticsService.registerHistory(gameId, isWin = false, duration, moves, undos, redos, occurred, userId)
    q.flatMap { _ =>
      Database.execute(GameHistoryQueries.SetCountsAndCompleted(gameId, moves, undos, redos, score, occurred, "resigned")).map(_ => "Resigned: Inserted")
    }
  }
}
