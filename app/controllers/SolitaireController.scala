package controllers

import utils.Application

import scala.concurrent.Future

@javax.inject.Singleton
class SolitaireController @javax.inject.Inject() (override val app: Application) extends BaseController {
  def solitaire = req("solitaire") { implicit request =>
    Future.successful(Ok(views.html.solitaire.solitaire(app.config.debug)))
  }
}
