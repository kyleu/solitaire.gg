package controllers

import play.api.i18n.MessagesApi
import play.api.mvc.Action
import services.EmailService

import scala.concurrent.Future

@javax.inject.Singleton
class HomeController @javax.inject.Inject() (override val messagesApi: MessagesApi, emailService: EmailService) extends BaseController {
  def index() = withSession { implicit request =>
    Future.successful(Ok(views.html.index(request.identity)))
  }

  def untrail(path: String) = Action.async {
    Future.successful(MovedPermanently(s"/$path"))
  }

  def externalLink(url: String) = Action.async {
    Future.successful(Redirect(if(url.startsWith("http")) { url } else { "http://" + url }))
  }

  def about = withSession { implicit request =>
    Future.successful(Ok(views.html.about(request.identity)))
  }
}
