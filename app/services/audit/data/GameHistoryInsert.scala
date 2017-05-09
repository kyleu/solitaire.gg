package services.audit.data

import java.util.UUID

import models.history.GameHistory
import models.queries.history.GameHistoryQueries
import models.queries.user.UserQueries
import models.user.User
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsNumber, JsValue}
import services.database.Database
import services.user.UserService
import utils.DateUtils

import scala.concurrent.Future

object GameHistoryInsert {
  def insert(id: UUID, data: JsValue): Future[String] = {
    val o = data.as[Map[String, JsValue]]
    o.get("gameId").map(_.as[UUID]).map { gameId =>
      val deviceId = o.get("deviceId").map(_.as[UUID]).getOrElse(User.defaultId)
      val userId = o.get("userId").map(_.as[UUID]).getOrElse(deviceId)
      val userQuery = Database.query(UserQueries.count(userId)).flatMap {
        case 0 => UserService.save(User(id = userId)).map(_ => true)
        case _ => Future.successful(false)
      }

      userQuery.flatMap { userCreated =>
        val seed = o.getOrElse("seed", JsNumber(-1)).as[Int]
        val rules = o("rules").as[String]
        val occurred = AnalyticsDataInsert.getDate(o).getOrElse(DateUtils.fromMillis(0))
        val q = GameHistoryQueries.insert(GameHistory(gameId, rules, seed, "started", userId, 0, 0, 0, 0, occurred, None, None, None))
        Database.execute(q).map(_ => s"Start: Inserted (User created: $userCreated)")
      }
    }.getOrElse(Future.successful("Start: Skipped"))
  }
}
