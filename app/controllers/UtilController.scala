package controllers

import util.Application

import scala.concurrent.Future

@javax.inject.Singleton
class UtilController @javax.inject.Inject() (override val app: Application) extends BaseController {
  def untrail(path: String) = Action.async {
    Future.successful(MovedPermanently(s"/$path"))
  }

  def externalLink(url: String) = req("external.link") { implicit request =>
    Future.successful(Redirect(if (url.startsWith("http")) { url } else { "http://" + url }))
  }

  def robots = Action.async {
    Future.successful(Ok("User-agent: *\nDisallow:\n"))
  }

  def noop = Action(parse.anyContent) { _ =>
    Ok("Please refresh the page, this url is no longer used.")
  }
}
