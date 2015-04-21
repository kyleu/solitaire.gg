package controllers.admin


import controllers.BaseController
import controllers.BaseController.AuthenticatedAction
import utils.parser.ScalaExporter
import utils.parser.politaire.PolitaireParser

object RulesController extends BaseController {
  def politaire = AuthenticatedAction { implicit request =>
    requireAdmin {
      Ok(views.html.admin.politaireList(PolitaireParser.politaireList))
    }
  }

  def rules = AuthenticatedAction { implicit request =>
    requireAdmin {
      Ok(views.html.admin.rulesList(PolitaireParser.gameRules))
    }
  }

  def exportRules = AuthenticatedAction { implicit request =>
    val rulesSet = PolitaireParser.gameRules
    ScalaExporter.export(rulesSet)
    Ok("OK!")
  }
}
