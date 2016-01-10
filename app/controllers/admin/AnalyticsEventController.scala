package controllers.admin

import java.util.UUID

import controllers.BaseController
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.analytics.AnalyticsExport
import services.audit.{ AnalyticsService, ClientTraceService }
import utils.ApplicationContext

import utils.DateUtils._

@javax.inject.Singleton
class AnalyticsEventController @javax.inject.Inject() (override val ctx: ApplicationContext) extends BaseController {
  def exportStatus() = withAdminSession("list") { implicit request =>
    implicit val identity = request.identity
    val export = new AnalyticsExport(ctx.config)
    val persistedDates = export.getPersistedDateCounts

    persistedDates.map { persisted =>
      val fsd = export.getFileSystemDetails

      val combinedDates = (fsd.map(_._1) ++ persisted.map(_._1)).distinct.sorted
      val messages = combinedDates.map { d =>
        val p = persisted.find(_._1 == d)
        val f = fsd.find(_._1 == d)

        (d, p.map(_._2).getOrElse(0), f.map(_._2).getOrElse(Nil))
      }

      Ok(views.html.admin.analytics.exportStatus(messages))
    }

  }

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
