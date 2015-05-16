package controllers

import models.database.queries.auth.ProfileQueries
import models.game.rules.GameRulesSet
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.Action
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
    Future.successful(Ok(views.html.game.gameplay(request.identity, "klondike", Seq("start"))))
  }

  def newGame(rules: String) = withSession { implicit request =>
    Future.successful(Ok(views.html.game.gameplay(request.identity, rules, Seq("start"))))
  }

  def newGameWithSeed(rules: String, seed: Int) = withSession { implicit request =>
    Future.successful(Ok(views.html.game.gameplay(request.identity, rules, Seq("start"), Some(seed))))
  }

  def newDefaultOfflineGame() = withSession { implicit request =>
    Future.successful(Ok(views.html.game.gameplay(request.identity, "klondike", Seq("start"), offline = true)))
  }

  def newOfflineGame(rules: String) = withSession { implicit request =>
    Future.successful(Ok(views.html.game.gameplay(request.identity, rules, Seq("start"), offline = true)))
  }

  def newOfflineGameWithSeed(rules: String, seed: Int) = withSession { implicit request =>
    Future.successful(Ok(views.html.game.gameplay(request.identity, rules, Seq("start"), seed = Some(seed), offline = true)))
  }
}
