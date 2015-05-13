package controllers

import models.game.rules.GameRulesSet
import play.api.mvc.Action

import scala.concurrent.Future

object HomeController extends BaseController {
  def index() = UserAwareAction.async { implicit request =>
    Future.successful(Ok(views.html.index(request.identity)))
  }

  def untrail(path: String) = Action.async {
    Future.successful(MovedPermanently("/" + path))
  }

  def profile = UserAwareAction.async { implicit request =>
    Future.successful(request.identity match {
      case Some(u) => Ok(u.fullName.getOrElse("!!"))
      case None => Ok("Nope!")
    })
  }

  def help(id: String) = UserAwareAction.async { implicit request =>
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

  def newDefaultGame() = UserAwareAction.async { implicit request =>
    Future.successful(Ok(views.html.game.gameplay(request.identity, "klondike", Seq("start"))))
  }

  def newGame(rules: String) = UserAwareAction.async { implicit request =>
    Future.successful(Ok(views.html.game.gameplay(request.identity, rules, Seq("start"))))
  }

  def newGameWithSeed(rules: String, seed: Int) = UserAwareAction.async { implicit request =>
    Future.successful(Ok(views.html.game.gameplay(request.identity, rules, Seq("start"), Some(seed))))
  }

  def newDefaultOfflineGame() = UserAwareAction.async { implicit request =>
    Future.successful(Ok(views.html.game.gameplay(request.identity, "klondike", Seq("start"), offline = true)))
  }

  def newOfflineGame(rules: String) = UserAwareAction.async { implicit request =>
    Future.successful(Ok(views.html.game.gameplay(request.identity, rules, Seq("start"), offline = true)))
  }

  def newOfflineGameWithSeed(rules: String, seed: Int) = UserAwareAction.async { implicit request =>
    Future.successful(Ok(views.html.game.gameplay(request.identity, rules, Seq("start"), seed = Some(seed), offline = true)))
  }
}
