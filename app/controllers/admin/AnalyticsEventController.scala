package controllers.admin

import java.util.UUID

import controllers.BaseController
import models.audit.AnalyticsEvent
import models.queries.audit.AnalyticsEventQueries
import models.queries.history.{GameHistoryQueries, InstallHistoryQueries, OpenHistoryQueries}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.audit.AnalyticsService
import services.audit.data.AnalyticsDataInsert
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

  def sandbox(reset: Boolean, limit: Int) = withAdminSession("analytics.sandbox") { implicit request =>
    val offset = 0

    val startMs = System.currentTimeMillis

    val wipe = if (reset) {
      Database.execute(OpenHistoryQueries.truncate).flatMap { _ =>
        Database.execute(InstallHistoryQueries.truncate).flatMap { _ =>
          Database.execute(GameHistoryQueries.truncate)
        }
      }
    } else {
      Future.successful(0)
    }

    val resetStartMs = System.currentTimeMillis
    wipe.flatMap { _ =>
      val resetTime = if (reset) {
        Some(System.currentTimeMillis - resetStartMs)
      } else {
        None
      }
      val queryStartMs = System.currentTimeMillis
      Database.query(AnalyticsEventQueries.GetEvents(limit = limit, offset = offset)).flatMap { events =>
        val queryTime = System.currentTimeMillis - queryStartMs
        val processingStart = System.currentTimeMillis
        val result = events.foldLeft(Future.successful(Seq.empty[(AnalyticsEvent, String)])) { (x, y) =>
          x.flatMap { ret =>
            AnalyticsDataInsert.process(y).map(x => ret :+ x)
          }
        }
        val processingTime = System.currentTimeMillis - processingStart

        result.map { r =>
          Ok(views.html.admin.analytics.sandbox(startMs, r, queryTime, resetTime, processingTime))
        }
      }
    }
  }
}
