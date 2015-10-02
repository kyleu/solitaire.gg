package services.audit

import java.util.UUID

import models.audit.AnalyticsEvent
import models.queries.audit.AnalyticsEventQueries
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.JsValue
import services.database.Database
import utils.DateUtils

object AnalyticsService {
  def install(device: UUID, data: JsValue) = log(AnalyticsEvent.EventType.Install, device, data)
  def gameStart(device: UUID, data: JsValue) = log(AnalyticsEvent.EventType.GameStart, device, data)
  def gameWon(device: UUID, data: JsValue) = log(AnalyticsEvent.EventType.GameWon, device, data)
  def gameResigned(device: UUID, data: JsValue) = log(AnalyticsEvent.EventType.GameResigned, device, data)

  private[this] def log(eventType: AnalyticsEvent.EventType, device: UUID, data: JsValue) = {
    val event = AnalyticsEvent(
      id = UUID.randomUUID(),
      eventType = eventType,
      device = device,
      data = data,
      created = DateUtils.now
    )
    Database.execute(AnalyticsEventQueries.insert(event)).map(x => event)
  }

  def searchEvents(q: String, orderBy: String, page: Int) = for {
    count <- Database.query(AnalyticsEventQueries.searchCount(q))
    list <- Database.query(AnalyticsEventQueries.search(q, orderBy, Some(page)))
  } yield count -> list


  def remove(id: UUID) = Database.execute(AnalyticsEventQueries.removeById(id))
}
