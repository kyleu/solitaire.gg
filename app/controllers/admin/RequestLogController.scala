package controllers.admin

import controllers.BaseController
import models.database.queries.RequestLogQueries
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

object RequestLogController extends BaseController {
  def requestList(q: String, sortBy: String, page: Int) = withAdminSession { implicit request =>
    Database.query(RequestLogQueries.CountQuery(q)).flatMap { count =>
      Database.query(RequestLogQueries.SearchQuery(q, getOrderClause(sortBy), Some(page))).map { requests =>
        Ok(views.html.admin.request.requestList(q, sortBy, count, page, requests))
      }
    }
  }

  private[this] def getOrderClause(orderBy: String) = orderBy match {
    case "occurred" => "occurred desc"
    case x => x
  }
}
