package controllers

import java.util.UUID

import models.database.queries.UserFeedbackQueries
import models.user.UserFeedback
import org.joda.time.LocalDateTime
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.Action
import services.database.Database

import scala.concurrent.Future

object HomeController extends BaseController {
  def index() = withSession { implicit request =>
    Future.successful(Ok(views.html.index(request.identity)))
  }

  def untrail(path: String) = Action.async {
    Future.successful(MovedPermanently("/" + path))
  }

  def feedbackForm = withSession { implicit request =>
    Future.successful(Ok(views.html.feedback()))
  }

  def submitFeedback = withSession { implicit request =>
    request.body.asFormUrlEncoded match {
      case Some(form) => form.get("feedback") match {
        case Some(feedback) =>
          val obj = UserFeedback(
            id = UUID.randomUUID,
            userId = request.identity.id,
            activeGameId = None,
            feedback = feedback.mkString("\n\n"),
            occurred = new LocalDateTime()
          )
          Database.execute(UserFeedbackQueries.CreateUserFeedback(obj)).map { x =>
            Redirect(routes.HomeController.feedbackForm()).flashing("success" -> "Your feedback has been submitted. Thanks!")
          }
        case None => Future.successful(Redirect(routes.HomeController.feedbackForm()).flashing("error" -> "Please include some feedback."))
      }
      case None => Future.successful(Redirect(routes.HomeController.feedbackForm()).flashing("error" -> "Please include some feedback."))
    }
  }
}
