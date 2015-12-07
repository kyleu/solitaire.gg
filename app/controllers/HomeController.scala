package controllers

import play.api.mvc.Action
import utils.ApplicationContext

import scala.concurrent.Future

@javax.inject.Singleton
class HomeController @javax.inject.Inject() (override val ctx: ApplicationContext) extends BaseController {
  def index() = withSession("index") { implicit request =>
    Future.successful(Ok(views.html.index(request.identity, false)))
  }

  def untrail(path: String) = Action.async {
    Future.successful(MovedPermanently(s"/$path"))
  }

  def externalLink(url: String) = withSession("external.link") { implicit request =>
    Future.successful(Redirect(if (url.startsWith("http")) { url } else { "http://" + url }))
  }

  def robots = Action.async {
    Future.successful(Ok("User-agent: *\nDisallow:\n"))
  }

  def about = withSession("about") { implicit request =>
    Future.successful(Ok(views.html.about(request.identity)))
  }

  def ping(timestamp: Long) = withSession("ping") { implicit request =>
    Future.successful(Ok(timestamp.toString))
  }

  def privacy() = withSession("privacy") { implicit request =>
    Future.successful(Ok(views.html.privacy(request.identity)))
  }
}
