package controllers

import play.api.mvc._

object HomeController extends Controller {
  def index() = Action { implicit request =>
    Ok(views.html.index())
  }

  def newDefaultGame() = Action { implicit request =>
    Ok(views.html.gameplay("klondike"))
  }

  def newGame(variant: String) = Action { implicit request =>
    Ok(views.html.gameplay(variant))
  }

  def newGameWithSeed(variant: String, seed: Int) = Action { implicit request =>
    Ok(views.html.gameplay(variant, Some(seed)))
  }
}
