package controllers.admin

import controllers.BaseController
import models.database.queries.UserFeedbackQueries
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

class FeedbackController @javax.inject.Inject() (val messagesApi: MessagesApi) extends BaseController {
  def feedbackList(q: String, sortBy: String, page: Int) = withAdminSession { implicit request =>
    Database.query(UserFeedbackQueries.count(q)).flatMap { count =>
      Database.query(UserFeedbackQueries.search(q, getOrderClause(sortBy), Some(page))).map { list =>
        Ok(views.html.admin.feedback.feedbackList(q, sortBy, count, page, list))
      }
    }
  }

  private[this] def getOrderClause(orderBy: String) = orderBy match {
    case x => x
  }
}
