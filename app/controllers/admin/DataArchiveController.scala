package controllers.admin

import controllers.BaseController
import org.joda.time.{ Period, LocalDate }
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.history.DataArchiveService
import services.user.AuthenticationEnvironment
import utils.DateUtils

@javax.inject.Singleton
class DataArchiveController @javax.inject.Inject() (
  override val messagesApi: MessagesApi,
  override val env: AuthenticationEnvironment
) extends BaseController {

  def list() = withAdminSession("data.archive.list") { implicit request =>
    DataArchiveService.getAll.map { archives =>
      val startDate = new LocalDate("2015-07-01")
      val days = (0 to Period.fieldDifference(startDate, DateUtils.today).getDays).map { i =>
        startDate.plusDays(i)
      }
      Ok(views.html.admin.dataArchive.archiveList(days, archives))
    }
  }

  def process(day: LocalDate) = withAdminSession("data.archive.process") { implicit request =>
    val startMs = System.currentTimeMillis
    DataArchiveService.process(day).map { result =>
      Redirect(routes.DataArchiveController.list()).flashing("success" -> s"Processed [$day] in [${System.currentTimeMillis - startMs}ms].")
    }
  }
}
