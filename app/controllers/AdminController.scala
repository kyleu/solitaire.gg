package controllers

import play.api.mvc._

object AdminController extends Controller {
  def index = Action { implicit request =>
    Ok("Admin!")
  }
}
