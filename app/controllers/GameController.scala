package controllers

import models.rules.GameRulesSet
import play.api.i18n.Messages
import play.api.mvc.{Request, AnyContent}
import utils.{Config, Application}

import scala.concurrent.Future

@javax.inject.Singleton
class GameController @javax.inject.Inject() (override val app: Application) extends BaseController {
  def solitaire = req("solitaire") { implicit request =>
    Future.successful(Ok(views.html.solitaire(app.config.debug)))
  }

  def help(id: String, inline: Boolean) = req("help." + id) { implicit request =>
    Future.successful {
      id match {
        case "undefined" => Ok(Messages("help.general", Config.projectName))
        case _ => GameRulesSet.allByIdWithAliases.get(id) match {
          case Some(rules) => if (inline) {
            Ok(views.html.help.helpInline(rules))
          } else {
            Ok(views.html.help.helpPage(rules))
          }
          case None => Ok(Messages("invalid.game.rules", id))
        }
      }
    }
  }

  def newGame(rules: String) = req(s"new.$rules.offline") { implicit request =>
    startGame(rules)
  }

  def newGameWithSeed(rules: String, seed: Int) = req(s"new.$rules.offline.withseed") { implicit request =>
    startGame(rules, seed = Some(seed))
  }

  def newVrGame(rules: String) = req(s"new.$rules.vr") { implicit request =>
    Future.successful(GameRulesSet.allByIdWithAliases.get(rules) match {
      case Some(r) =>
        val title = if (rules == r.id) { r.title } else { r.aka(rules) }
        Ok(views.html.vr.vr(title, rules, r.description, None, app.config.debug))
      case None => NotFound(Messages("invalid.game.rules", rules))
    })
  }

  private[this] def startGame(
    rulesId: String,
    initialAction: Seq[String] = Seq("start"),
    seed: Option[Int] = None
  )(implicit request: Request[AnyContent]) = {
    Future.successful(GameRulesSet.allByIdWithAliases.get(rulesId) match {
      case Some(rules) =>
        val title = if (rulesId == rules.id) { rules.title } else { rules.aka(rulesId) }
        Ok(views.html.game.gameplay(title, rulesId, rules.description, initialAction, seed, app.config.debug))
      case None => NotFound(Messages("invalid.game.rules", rulesId))
    })
  }
}
