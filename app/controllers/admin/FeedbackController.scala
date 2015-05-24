package controllers.admin

import controllers.BaseController
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.user.UserFeedbackService

object FeedbackController extends BaseController {
  def feedbackList(q: String, sortBy: String) = withAdminSession { implicit request =>
    UserFeedbackService.searchFeedback(q, sortBy).map { feedbacks =>
      Ok(views.html.admin.feedback.feedbackList(sortBy, feedbacks))
    }
  }
}
