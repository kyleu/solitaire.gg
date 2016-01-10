package controllers.admin

import java.io.FileOutputStream

import controllers.BaseController
import models.queries.audit.AnalyticsEventQueries
import org.joda.time.LocalDate
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.iteratee.Enumerator
import play.api.mvc.{ ResponseHeader, Result }
import services.analytics.AnalyticsExport
import services.database.Database
import utils.{ FileUtils, ApplicationContext }
import utils.DateUtils._

import scala.concurrent.Future

@javax.inject.Singleton
class AnalyticsExportController @javax.inject.Inject() (override val ctx: ApplicationContext) extends BaseController {
  val export = new AnalyticsExport(ctx.config)

  def exportStatus() = withAdminSession("status") { implicit request =>
    implicit val identity = request.identity
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
    val existing = FileUtils.listFiles(export.dirFor(d))
    if (existing.isEmpty) {
      Database.query(AnalyticsEventQueries.GetByDate(d)).map { result =>
        val openFile = new FileOutputStream(export.getLogFile(d, "open.log"))
        result.foreach { event =>
          println("!!!")
          openFile.write(event.id.toString.getBytes())
          openFile.write("\n".getBytes())
        }
        openFile.close()
        Redirect(controllers.admin.routes.AnalyticsExportController.exportStatus()).flashing("success" -> s"[0] files cached for [$d].")
      }
    } else {
      val msg = s"There are already [${existing.size}] existing files for [$d]."
      Future.successful(Redirect(controllers.admin.routes.AnalyticsExportController.exportStatus()).flashing("error" -> msg))
    }
  }

  def removeFiles(d: LocalDate) = withAdminSession("remove-files") { implicit request =>
    Future.successful {
      val num = export.removeFiles(d)
      Redirect(controllers.admin.routes.AnalyticsExportController.exportStatus()).flashing("success" -> s"Removed [$num] files for [$d].")
    }
  }

  def downloadFile(d: LocalDate, name: String) = withAdminSession("download-file") { implicit request =>
    Future.successful {
      val f = export.getLogFile(d, name)
      if (!f.exists()) {
        throw new IllegalStateException(s"File [${f.getPath}] does not exist.")
      }
      val fileContent: Enumerator[Array[Byte]] = Enumerator.fromFile(f)
      Result(
        header = ResponseHeader(200),
        body = fileContent
      )
    }
  }

  def wipeDatabase(d: LocalDate) = withAdminSession("wipe") { implicit request =>
    Future.successful {
      Redirect(controllers.admin.routes.AnalyticsExportController.exportStatus()).flashing("error" -> s"No.")
    }
  }
}
