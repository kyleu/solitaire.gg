package controllers.admin

import controllers.BaseController
import services.analytics.AnalyticsFileService
import utils.ApplicationContext

import scala.concurrent.Future

@javax.inject.Singleton
class AnalyticsFileController @javax.inject.Inject() (override val ctx: ApplicationContext) extends BaseController {
  val fileService = new AnalyticsFileService(ctx.config)

  def overview() = withAdminSession("overview") { implicit request =>
    Future.successful {
      val ret = fileService.getFileSystemDetails.map { fsd =>
        fsd._1 -> fileService.lineCounts(fsd._1)
      }
      Ok(views.html.admin.analytics.fileOverview(ret))
    }
  }
}
