package controllers

import models.database.queries.auth.ProfileQueries
import models.game.rules.GameRulesSet
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.{ AnyContent, Action }
import services.database.Database

import scala.concurrent.Future

object HomeController extends BaseController {
  def index() = withSession { implicit request =>
    Future.successful(Ok(views.html.index(request.identity)))
  }

  def untrail(path: String) = Action.async {
    Future.successful(MovedPermanently("/" + path))
  }

  def profile = withSession { implicit request =>
    Database.query(ProfileQueries.FindProfilesByUser(request.identity.id)).map { profiles =>
      Ok(views.html.profile(request.identity, profiles))
    }
  }

  def help(id: String) = withSession { implicit request =>
    Future.successful {
      id match {
        case "undefined" => Ok("Join a game before you request help!")
        case _ => GameRulesSet.allById.get(id) match {
          case Some(rules) => Ok(views.html.help(rules))
          case None => Ok("We can't find any information about [" + id + "].")
        }
      }
    }
  }

  def newDefaultGame() = withSession { implicit request =>
    startGame()
  }

  def newGame(rules: String) = withSession { implicit request =>
    startGame(rules)
  }

  def newGameWithSeed(rules: String, seed: Int) = withSession { implicit request =>
    startGame(rules, seed = Some(seed))
  }

  def newDefaultOfflineGame() = withSession { implicit request =>
    startGame(offline = true)
  }

  def newOfflineGame(rules: String) = withSession { implicit request =>
    startGame(rules, offline = true)
  }

  def newOfflineGameWithSeed(rules: String, seed: Int) = withSession { implicit request =>
    startGame(rules, seed = Some(seed), offline = true)
  }

  private[this] def startGame(
    rulesId: String = "klondike",
    initialAction: Seq[String] = Seq("start"),
    seed: Option[Int] = None,
    offline: Boolean = false
  )(implicit request: SecuredRequest[AnyContent]) = {
    Future.successful(GameRulesSet.allById.get(rulesId) match {
      case Some(rules) => Ok(views.html.game.gameplay(rules.title, request.identity, rulesId, initialAction))
      case None => NotFound("Unknown game [" + rulesId + "].")
    })
  }
}
