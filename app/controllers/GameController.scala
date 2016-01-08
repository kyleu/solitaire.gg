package controllers

import models.rules.GameRulesSet
import play.api.i18n.Messages
import play.api.mvc.{ Request, AnyContent }
import utils.{ Config, ApplicationContext }

import scala.concurrent.Future

@javax.inject.Singleton
class GameController @javax.inject.Inject() (override val ctx: ApplicationContext) extends BaseController {
  def help(id: String, inline: Boolean) = withSession("help." + id) { implicit request =>
    Future.successful {
      id match {
        case "undefined" => Ok(Messages("help.general", Config.projectName))
        case _ => GameRulesSet.allByIdWithAliases.get(id) match {
          case Some(rules) => if (inline) {
            Ok(views.html.help.helpInline(rules, request.identity.preferences.color))
          } else {
            Ok(views.html.help.helpPage(request.identity, rules))
          }
          case None => Ok(Messages("invalid.game.rules", id))
        }
      }
    }
  }

  def newGame(rules: String) = withSession(s"new.$rules") { implicit request =>
    startGame(rules, offline = true)
  }

  def newGameWithSeed(rules: String, seed: Int) = withSession(s"new.$rules.withseed") { implicit request =>
    startGame(rules, seed = Some(seed), offline = true)
  }

  def newOfflineGame(rules: String) = withSession(s"new.$rules.offline") { implicit request =>
    startGame(rules, offline = true)
  }

  def newOfflineGameWithSeed(rules: String, seed: Int) = withSession(s"new.$rules.offline.withseed") { implicit request =>
    startGame(rules, seed = Some(seed), offline = true)
  }

  private[this] def shouldWorkaround(r: Request[AnyContent]) = {
    val ua = r.headers.get("User-Agent").getOrElse("")
    ua.contains("Chrome") && ua.contains("OS X")
  }

  private[this] def startGame(
    rulesId: String,
    initialAction: Seq[String] = Seq("start"),
    seed: Option[Int] = None,
    offline: Boolean = false
  )(implicit request: SecuredRequest[AnyContent]) = {
    Future.successful(GameRulesSet.allByIdWithAliases.get(rulesId) match {
      case Some(rules) =>
        val title = if (rulesId == rules.id) { rules.title } else { rules.aka(rulesId) }
        Ok(views.html.game.gameplay(
          title, request.identity, rulesId, rules.description, initialAction, seed, offline, ctx.config.debug, shouldWorkaround(request)
        ))
      case None => NotFound(Messages("invalid.game.rules", rulesId))
    })
  }
}
