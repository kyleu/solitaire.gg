package controllers.admin

import controllers.BaseController
import models.game.rules.GameRulesSet
import models.user.{Role, WithRole}
import parser.{ ScalaExporter, RulesReset }
import parser.politaire.{ LinkParser, PolitaireParser }
import play.twirl.api.Html

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

object RulesController extends BaseController {
  def politaire = SecuredAction(WithRole(Role.Admin)).async { implicit request =>
    Future.successful(Ok(views.html.admin.rules.politaireList(PolitaireParser.politaireList)))
  }

  def rules = SecuredAction(WithRole(Role.Admin)).async { implicit request =>
    Future.successful(Ok(views.html.admin.rules.rulesList(GameRulesSet.all)))
  }

  def importRules = SecuredAction(WithRole(Role.Admin)).async { implicit request =>
    Future.successful(Ok(views.html.admin.rules.rulesList(PolitaireParser.gameRules)))
  }

  def exportRules = SecuredAction(WithRole(Role.Admin)).async { implicit request =>
    Future {
      val rulesSet = PolitaireParser.gameRules
      ScalaExporter.export(rulesSet)
      Redirect(routes.RulesController.rules())
    }
  }

  def links = SecuredAction(WithRole(Role.Admin)).async { implicit request =>
    Future {
      val links = LinkParser.parse()
      val ret = "<ul>\n" + links.map(l => "\n  <li>" + l._1 + ": [" + l._2.mkString(" :: ") + "]</li>").mkString("\n") + "\n</ul>"
      Ok(Html(ret))
    }
  }

  def wipeRules = SecuredAction(WithRole(Role.Admin)).async { implicit request =>
    Future {
      RulesReset.go()
      Redirect(routes.RulesController.rules())
    }
  }
}
