package controllers

import play.api.mvc._
import utils.Config

object HomeController extends Controller {
  def index = Action {
    Ok(views.html.index(Config.debug))
  }
}
