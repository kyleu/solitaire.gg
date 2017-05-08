package controllers.admin

import java.util.UUID

import controllers.BaseController
import models.audit.AnalyticsEvent
import models.queries.audit.AnalyticsEventQueries
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.audit.AnalyticsService
import services.database.Database
import utils.Application

import scala.concurrent.Future

@javax.inject.Singleton
class AnalyticsEventController @javax.inject.Inject() (override val app: Application) extends BaseController {
  def eventList(q: String, sortBy: String, page: Int) = withAdminSession("list") { implicit request =>
    AnalyticsService.searchEvents(q, sortBy, page).map {
      case (count, traces) => Ok(views.html.admin.analytics.eventList(q, sortBy, count, page, traces))
    }
  }

  def eventDetail(id: UUID) = withAdminSession("detail") { implicit request =>
    Future.successful(Ok(s"TODO: $id"))
  }

  def removeEvent(id: UUID) = withAdminSession("remove") { implicit request =>
    AnalyticsService.remove(id).map { ok =>
      Redirect(controllers.admin.routes.AnalyticsEventController.eventList(s"TODO: $id"))
    }
  }

  def sandbox(reset: Boolean) = withAdminSession("analytics.sandbox") { implicit request =>
    val limit = 1000
    val offset = 0

    val startMs = System.currentTimeMillis
    Database.query(AnalyticsEventQueries.GetEvents(limit = limit, offset = offset)).map { events =>
      val queryTime = System.currentTimeMillis - startMs
      val resetTime = if (reset) {
        val resetStartMs = System.currentTimeMillis
        // TODO Wipe
        Some(System.currentTimeMillis - resetStartMs)
      } else {
        None
      }
      val processingStart = System.currentTimeMillis

      val processingTime = System.currentTimeMillis - processingStart

      val result = events.map(process)

      Ok(views.html.admin.analytics.sandbox(startMs, result, queryTime, resetTime, processingTime))
    }
  }

  private[this] def process(event: AnalyticsEvent) = {
    val msg = event.eventType match {
      case AnalyticsEvent.EventType.Error => None
      case AnalyticsEvent.EventType.Install => None
      case AnalyticsEvent.EventType.Open => None
      case AnalyticsEvent.EventType.GameStart => None
      case AnalyticsEvent.EventType.GameWon => None
      case AnalyticsEvent.EventType.GameResigned => None
      case u: AnalyticsEvent.EventType.Unknown => None
    }
    event -> msg.toString
  }
}
