package controllers.admin

import java.util.UUID

import controllers.BaseController
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.audit.{ AnalyticsService, ClientTraceService }
import utils.ApplicationContext

@javax.inject.Singleton
class AnalyticsEventController @javax.inject.Inject() (override val ctx: ApplicationContext) extends BaseController {
  def eventList(q: String, sortBy: String, page: Int) = withAdminSession("list") { implicit request =>
    implicit val identity = request.identity
    for {
      (count, traces) <- AnalyticsService.searchEvents(q, sortBy, page)
    } yield Ok(views.html.admin.analytics.eventList(q, sortBy, count, page, traces))
  }

  def eventDetail(id: UUID) = withAdminSession("detail") { implicit request =>
    implicit val identity = request.identity
    ClientTraceService.getTrace(id).map {
      case Some(trace) => Ok(views.html.admin.clientTrace.traceDetail(trace))
      case None => NotFound(s"User [$id] not found.")
    }
  }

  def removeEvent(id: UUID) = withAdminSession("remove") { implicit request =>
    AnalyticsService.remove(id).map { ok =>
      Redirect(controllers.admin.routes.AnalyticsEventController.eventList(""))
    }
  }
}
