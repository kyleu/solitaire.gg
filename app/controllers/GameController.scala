package controllers

import models.game.rules.GameRulesSet
import play.api.i18n.{ MessagesApi, Messages }
import play.api.mvc.AnyContent

import scala.concurrent.Future

@javax.inject.Inject
class GameController @javax.inject.Inject() (val messagesApi: MessagesApi) extends BaseController {
  def help(id: String) = withSession { implicit request =>
    Future.successful {
      id match {
        case "undefined" => Ok(Messages("help.general"))
        case _ => GameRulesSet.allById.get(id) match {
          case Some(rules) => Ok(views.html.game.rulesHelp(rules))
          case None => Ok(Messages("invalid.game.rules", id))
        }
      }
    }
  }

  def newGame(rules: String) = withSession { implicit request =>
    startGame(rules)
  }

  def newGameWithSeed(rules: String, seed: Int) = withSession { implicit request =>
    startGame(rules, seed = Some(seed))
  }

  def newOfflineGame(rules: String) = withSession { implicit request =>
    startGame(rules, offline = true)
  }

  def newOfflineGameWithSeed(rules: String, seed: Int) = withSession { implicit request =>
    startGame(rules, seed = Some(seed), offline = true)
  }

  private[this] def startGame(
    rulesId: String,
    initialAction: Seq[String] = Seq("start"),
    seed: Option[Int] = None,
    offline: Boolean = false
  )(implicit request: SecuredRequest[AnyContent]) = {
    Future.successful(GameRulesSet.allById.get(rulesId) match {
      case Some(rules) => Ok(views.html.game.gameplay(rules.title, request.identity, rulesId, initialAction, seed, offline))
      case None => NotFound(Messages("invalid.game.rules", rulesId))
    })
  }
}
