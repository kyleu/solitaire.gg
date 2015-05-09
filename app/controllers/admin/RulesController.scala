package controllers.admin

import controllers.BaseController
import controllers.BaseController.AuthenticatedAction
import models.game.rules.GameRulesSet
import parser.{ ScalaExporter, RulesReset }
import parser.politaire.{ LinkParser, PolitaireParser }
import play.twirl.api.Html

object RulesController extends BaseController {
  def politaire = AuthenticatedAction { implicit request =>
    requireAdmin {
      Ok(views.html.admin.rules.politaireList(PolitaireParser.politaireList))
    }
  }

  def rules = AuthenticatedAction { implicit request =>
    requireAdmin {
      Ok(views.html.admin.rules.rulesList(GameRulesSet.all))
    }
  }

  def importRules = AuthenticatedAction { implicit request =>
    requireAdmin {
      Ok(views.html.admin.rules.rulesList(PolitaireParser.gameRules))
    }
  }

  def exportRules = AuthenticatedAction { implicit request =>
    val rulesSet = PolitaireParser.gameRules
    ScalaExporter.export(rulesSet)
    Redirect(routes.RulesController.rules())
  }

  def links = AuthenticatedAction { implicit request =>
    val links = LinkParser.parse()
    val ret = "<ul>\n" + links.map(l => "\n  <li>" + l._1 + ": [" + l._2.mkString(" :: ") + "]</li>").mkString("\n") + "\n</ul>"
    Ok(Html(ret))
  }

  def wipeRules = AuthenticatedAction { implicit request =>
    RulesReset.go()
    Redirect(routes.RulesController.rules())
  }
}
