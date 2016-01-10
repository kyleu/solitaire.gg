package controllers.admin

import controllers.BaseController
import org.joda.time.LocalDate
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.analytics.AnalyticsExport
import utils.ApplicationContext
import utils.DateUtils._

import scala.concurrent.Future

@javax.inject.Singleton
class AnalyticsExportController @javax.inject.Inject() (override val ctx: ApplicationContext) extends BaseController {
  def exportStatus() = withAdminSession("status") { implicit request =>
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

  def cacheFiles(d: LocalDate) = withAdminSession("cache") { implicit request =>
    implicit val identity = request.identity
    Future.successful {
      Redirect(controllers.admin.routes.AnalyticsExportController.exportStatus()).flashing("success" -> s"[0] files cached for [$d].")
    }
  }

  def wipeDatabase(d: LocalDate) = withAdminSession("wipe") { implicit request =>
    implicit val identity = request.identity
    Future.successful {
      Redirect(controllers.admin.routes.AnalyticsExportController.exportStatus()).flashing("error" -> s"No.")
    }
  }
}
