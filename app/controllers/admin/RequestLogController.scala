package controllers.admin

import controllers.BaseController
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.audit.RequestLogService

object RequestLogController extends BaseController {
  def requestList(sortBy: String) = AdminAction.async { implicit request =>
    RequestLogService.recentLogs().map { requests =>
      Ok(views.html.admin.requestList(sortBy, requests))
    }
  }
}
