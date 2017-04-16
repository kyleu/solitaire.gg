package controllers

import play.api.mvc.Action
import utils.Application

import scala.concurrent.Future

@javax.inject.Singleton
class HomeController @javax.inject.Inject() (override val app: Application) extends BaseController {
  def index() = req("index") { implicit request =>
    Future.successful(Ok(views.html.index(isAdmin = false)))
  }

  def untrail(path: String) = Action.async {
    Future.successful(MovedPermanently(s"/$path"))
  }

  def externalLink(url: String) = req("external.link") { implicit request =>
    Future.successful(Redirect(if (url.startsWith("http")) { url } else { "http://" + url }))
  }

  def robots = Action.async {
    Future.successful(Ok("User-agent: *\nDisallow:\n"))
  }

  def about = req("about") { implicit request =>
    Future.successful(Ok(views.html.about()))
  }

  def noop = Action { request =>
    Ok("Please refresh the page, this url is no longer used.")
  }

  def ping(timestamp: Long) = req("ping") { implicit request =>
    Future.successful(Ok(timestamp.toString))
  }

  def privacy() = req("privacy") { implicit request =>
    Future.successful(Ok(views.html.privacy()))
  }
}
