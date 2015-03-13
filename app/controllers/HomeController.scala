package controllers

import play.api.mvc._

object HomeController extends Controller {
  def index() = Action { implicit request =>
    Ok(views.html.index())
  }

  def newGame(variant: String = "klondike") = Action { implicit request =>
    Ok(views.html.gameplay(variant))
  }
}
