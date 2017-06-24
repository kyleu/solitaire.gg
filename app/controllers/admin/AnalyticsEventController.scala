package controllers.admin

import java.util.UUID

import controllers.BaseController
import models.queries.audit.AnalyticsEventQueries
import models.queries.history.{GameHistoryQueries, InstallHistoryQueries, OpenHistoryQueries}
import utils.FutureUtils.defaultContext
import play.twirl.api.Html
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
    AnalyticsService.remove(id).map { _ =>
      Redirect(controllers.admin.routes.AnalyticsEventController.eventList(s"TODO: $id"))
    }
  }

  def wipe() = {
    Database.execute(OpenHistoryQueries.truncate).flatMap { _ =>
      Database.execute(InstallHistoryQueries.truncate).flatMap { _ =>
        Database.execute(GameHistoryQueries.truncate)
      }
    }
  }

  def sandbox(test: Boolean, reset: Boolean, limit: Int, offset: Int) = withAdminSession("analytics.sandbox") { implicit request =>
    val f = if (test) { AnalyticsDataInsert.test _ } else { AnalyticsDataInsert.process _ }
    wipe().flatMap { _ =>
      Database.query(AnalyticsEventQueries.ProcessEvents(f = f, limit = limit, offset = offset)).flatMap { events =>
        events.map { r =>
          Ok(Html(s"${utils.Formatter.withCommas(r)} analytics events processed."))
        }
      }
    }
  }
}
