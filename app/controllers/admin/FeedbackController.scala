package controllers.admin

import controllers.BaseController
import models.database.queries.UserFeedbackQueries
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

object FeedbackController extends BaseController {
  def feedbackList(q: String, sortBy: String, page: Int) = withAdminSession { implicit request =>
    Database.query(UserFeedbackQueries.CountQuery(q)).flatMap { count =>
      Database.query(UserFeedbackQueries.SearchQuery(q, getOrderClause(sortBy), Some(page))).map { list =>
        Ok(views.html.admin.feedback.feedbackList(q, sortBy, count, page, list))
      }
    }
  }

  private[this] def getOrderClause(orderBy: String) = orderBy match {
    case x => x
  }
}
