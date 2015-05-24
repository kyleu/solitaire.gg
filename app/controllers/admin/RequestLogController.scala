package controllers.admin

import controllers.BaseController
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.audit.RequestLogService

object RequestLogController extends BaseController {
  def requestList(q: String, sortBy: String) = withAdminSession { implicit request =>
    RequestLogService.recentLogs().map { requests =>
      Ok(views.html.admin.request.requestList(q, sortBy, requests))
    }
  }
}
