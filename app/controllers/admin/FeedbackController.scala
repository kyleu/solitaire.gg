package controllers.admin

import java.util.UUID

import controllers.BaseController
import models.database.queries.audit.{ UserFeedbackQueries, UserFeedbackNoteQueries }
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

import scala.concurrent.Future

@javax.inject.Singleton
class FeedbackController @javax.inject.Inject() (override val messagesApi: MessagesApi) extends BaseController {
  def feedbackList(q: String, sortBy: String, page: Int) = withAdminSession { implicit request =>
    for {
      count <- Database.query(UserFeedbackQueries.count(q))
      feedbacks <- Database.query(UserFeedbackQueries.search(q, getOrderClause(sortBy), Some(page)))
      notes <- Database.query(UserFeedbackNoteQueries.GetUserFeedbackNotes(feedbacks.map(_.id)))
    } yield Ok(views.html.admin.feedback.feedbackList(q, sortBy, count, page, feedbacks, notes))
  }

  def feedbackNoteForm(feedbackId: UUID) = withAdminSession { implicit request =>
    Future.successful(Ok("Coming soon!"))
  }

  def removeFeedback(id: UUID) = withAdminSession { implicit request =>
    Database.execute(UserFeedbackQueries.remove(Seq(id))).map { ok =>
      Redirect(controllers.admin.routes.FeedbackController.feedbackList("", "occurred", 0))
    }
  }

  private[this] def getOrderClause(orderBy: String) = orderBy match {
    case x => x
  }
}
