package controllers

import models.rules.GameRulesSet
import play.api.i18n.{ MessagesApi, Messages }
import play.api.mvc.AnyContent
import services.user.AuthenticationEnvironment

import scala.concurrent.Future

@javax.inject.Singleton
class GameController @javax.inject.Inject() (
  override val messagesApi: MessagesApi,
  override val env: AuthenticationEnvironment
) extends BaseController {
  def help(id: String, inline: Boolean) = withSession { implicit request =>
    Future.successful {
      id match {
        case "undefined" => Ok(Messages("help.general"))
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
    Future.successful(GameRulesSet.allByIdWithAliases.get(rulesId) match {
      case Some(rules) =>
        val title = if (rulesId == rules.id) { rules.title } else { rules.aka(rulesId) }
        Ok(views.html.game.gameplay(title, request.identity, rulesId, initialAction, seed, offline))
      case None => NotFound(Messages("invalid.game.rules", rulesId))
    })
  }
}
