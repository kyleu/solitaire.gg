package controllers.admin

import java.io.FileOutputStream

import controllers.BaseController
import models.queries.audit.AnalyticsEventQueries
import org.joda.time.LocalDate
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.iteratee.Enumerator
import play.api.libs.json.Json
import play.api.mvc.{ ResponseHeader, Result }
import services.analytics.AnalyticsExport
import services.database.Database
import utils.{ FileUtils, ApplicationContext }

import scala.concurrent.Future

@javax.inject.Singleton
class AnalyticsExportController @javax.inject.Inject() (override val ctx: ApplicationContext) extends BaseController {
  val export = new AnalyticsExport(ctx.config)

  def exportStatus() = withAdminSession("status") { implicit request =>
    export.getStatus.map { messages =>
      Ok(views.html.admin.analytics.exportStatus(messages))
    }
  }

  def cacheMissingFiles() = withAdminSession("cache-all") { implicit request =>
    export.getStatus.flatMap { s =>
      val futures = s.filter(x => x._2 > 0 && x._3.isEmpty).map { x =>
        cacheEvents(x._1)
      }
      val future = Future.sequence(futures)
      future.map { result =>
        val msg = s"[${result.sum}] files cached for [${result.size}] days."
        Redirect(controllers.admin.routes.AnalyticsExportController.exportStatus()).flashing("success" -> msg)
      }
    }
  }

  def cacheFiles(d: LocalDate) = withAdminSession("cache") { implicit request =>
    val existing = FileUtils.listFiles(export.fileService.dirFor(d))
    if (existing.isEmpty) {
      cacheEvents(d).map(x => Redirect(controllers.admin.routes.AnalyticsExportController.exportStatus()).flashing("success" -> s"[$x] files cached for [$d]."))
    } else {
      val msg = s"There are already [${existing.size}] existing files for [$d]."
      Future.successful(Redirect(controllers.admin.routes.AnalyticsExportController.exportStatus()).flashing("error" -> msg))
    }
  }

  def removeFiles(d: LocalDate) = withAdminSession("remove-files") { implicit request =>
    Future.successful {
      val num = export.fileService.removeFiles(d)
      Redirect(controllers.admin.routes.AnalyticsExportController.exportStatus()).flashing("success" -> s"Removed [$num] files for [$d].")
    }
  }

  def removeAllFiles() = withAdminSession("remove-all-files") { implicit request =>
    Future.successful {
      val num = export.fileService.removeAllFiles()
      Redirect(controllers.admin.routes.AnalyticsExportController.exportStatus()).flashing("success" -> s"Removed [${num.sum}] files.")
    }
  }

  def downloadFile(d: LocalDate, name: String) = withAdminSession("download-file") { implicit request =>
    Future.successful {
      val f = export.fileService.getLogFile(d, name)
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

  private[this] def cacheEvents(d: LocalDate) = {
    val files = collection.mutable.HashMap.empty[String, FileOutputStream]
    Database.query(AnalyticsEventQueries.GetByDate(d)).map { result =>
      result.foreach { event =>
        val json = Json.toJson(event.data)
        val file = files.getOrElseUpdate(event.eventType.id, new FileOutputStream(export.fileService.getLogFile(d, event.eventType.id + ".log")))
        file.write(Json.stringify(json).getBytes())
        file.write("\n".getBytes())
      }
      files.mapValues(_.close())
      files.size
    }
  }
}
