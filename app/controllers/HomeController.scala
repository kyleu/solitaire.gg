package controllers

import play.api.mvc._

object HomeController extends Controller {
  def index(v: Option[String]) = Action { implicit request =>
    Ok(views.html.index(v.getOrElse("klondike")))
  }
}
