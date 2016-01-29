package controllers.admin

import controllers.BaseController
import org.joda.time.LocalDate
import services.analytics.AnalyticsFileService
import utils.ApplicationContext

import scala.concurrent.Future

@javax.inject.Singleton
class AnalyticsFileController @javax.inject.Inject() (override val ctx: ApplicationContext) extends BaseController {
  val fileService = new AnalyticsFileService(ctx.config)

  def overview() = withAdminSession("overview") { implicit request =>
    import utils.DateUtils._
    Future.successful {
      val ret = fileService.getFileSystemDetails.map { fsd =>
        fsd._1 -> fileService.lineCounts(fsd._1)
      }.sortBy(_._1)
      Ok(views.html.admin.analytics.fileOverview(ret))
    }
  }

  def trends(key: String) = withAdminSession("trends") { implicit request =>
    Future.successful {
      Ok(views.html.admin.analytics.fileTrends(key))
    }
  }

  def summary(d: LocalDate) = withAdminSession("summary") { implicit request =>
    Future.successful {
      Ok(views.html.admin.analytics.fileDailySummary(d))
    }
  }

  def detail(d: LocalDate, key: String) = withAdminSession("detail") { implicit request =>
    Future.successful {
      val lines = fileService.getLines(d, key).toSeq
      Ok(views.html.admin.analytics.fileDetail(d, key, lines))
    }
  }
}
