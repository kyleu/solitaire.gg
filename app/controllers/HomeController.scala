package controllers

import java.util.UUID

import models.audit.UserFeedback
import models.database.queries.audit.UserFeedbackQueries
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.Action
import services.EmailService
import services.database.Database
import utils.DateUtils

import scala.concurrent.Future

@javax.inject.Singleton
class HomeController @javax.inject.Inject() (override val messagesApi: MessagesApi, emailService: EmailService) extends BaseController {
  def index() = withSession { implicit request =>
    Future.successful(Ok(views.html.index(request.identity)))
  }

  def untrail(path: String) = Action.async {
    Future.successful(MovedPermanently(s"/$path"))
  }

  def about = withSession { implicit request =>
    Future.successful(Ok(views.html.about(request.identity)))
  }

  def feedbackForm = withSession { implicit request =>
    Future.successful(Ok(views.html.feedback(request.identity)))
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
            occurred = DateUtils.now
          )

          emailService.feedbackSubmitted(obj, request.identity)

          Database.execute(UserFeedbackQueries.insert(obj)).map { x =>
            Redirect(routes.HomeController.feedbackForm()).flashing("success" -> "Your feedback has been submitted. Thanks!")
          }
        case None => Future.successful(Redirect(routes.HomeController.feedbackForm()).flashing("error" -> "Please include some feedback."))
      }
      case None => Future.successful(Redirect(routes.HomeController.feedbackForm()).flashing("error" -> "Please include some feedback."))
    }
  }
}
