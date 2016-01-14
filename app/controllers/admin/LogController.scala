package controllers.admin

import controllers.BaseController
import services.audit.LogService
import utils.ApplicationContext

import scala.concurrent.Future

@javax.inject.Singleton
class LogController @javax.inject.Inject() (override val ctx: ApplicationContext) extends BaseController {
  def list() = withAdminSession("log.list") { implicit request =>
    val files = LogService.listFiles()
    Future.successful(Ok(views.html.admin.log.logList(files)))
  }

  def view(name: String) = withAdminSession("log.view") { implicit request =>
    val logs = LogService.getLogs(name)
    Future.successful(Ok(views.html.admin.log.logView(name, logs)))
  }
}
