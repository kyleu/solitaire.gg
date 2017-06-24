package services.audit

import java.util.UUID

import models.audit.AnalyticsEvent
import models.audit.AnalyticsEvent.EventType
import models.queries.audit.AnalyticsEventQueries
import utils.FutureUtils.defaultContext
import play.api.libs.json.JsValue
import services.database.Database
import utils.DateUtils

object AnalyticsService {
  def error(device: UUID, sourceAddress: String, data: JsValue) = log(EventType.Error, device, sourceAddress, data)
  def install(device: UUID, sourceAddress: String, data: JsValue) = log(EventType.Install, device, sourceAddress, data)
  def open(device: UUID, sourceAddress: String, data: JsValue) = log(EventType.Open, device, sourceAddress, data)
  def gameStart(device: UUID, sourceAddress: String, data: JsValue) = log(EventType.GameStart, device, sourceAddress, data)
  def gameWon(device: UUID, sourceAddress: String, data: JsValue) = log(EventType.GameWon, device, sourceAddress, data)
  def gameResigned(device: UUID, sourceAddress: String, data: JsValue) = log(EventType.GameResigned, device, sourceAddress, data)

  private[this] def log(eventType: AnalyticsEvent.EventType, device: UUID, sourceAddress: String, data: JsValue) = {
    val event = AnalyticsEvent(
      id = UUID.randomUUID(),
      eventType = eventType,
      device = device,
      sourceAddress = Some(sourceAddress),
      data = data,
      created = DateUtils.now
    )
    Database.execute(AnalyticsEventQueries.insert(event)).map(_ => event)
  }

  def searchEvents(q: String, orderBy: String, page: Int) = Database.query(AnalyticsEventQueries.searchCount(q)).flatMap { count =>
    Database.query(AnalyticsEventQueries.search(q, orderBy, Some(page))).map { list =>
      count -> list
    }
  }

  def remove(id: UUID) = Database.execute(AnalyticsEventQueries.removeById(id))
}
