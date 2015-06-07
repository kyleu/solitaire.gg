package controllers.admin

import controllers.BaseController
import models.game.rules.GameRulesSet
import parser.{ ScalaExporter, RulesReset }
import parser.politaire.{ LinkParser, PolitaireParser }
import play.api.i18n.MessagesApi
import play.twirl.api.Html

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class RulesController @javax.inject.Inject() (val messagesApi: MessagesApi) extends BaseController {
  def politaire = withAdminSession { implicit request =>
    Future.successful(Ok(views.html.admin.rules.politaireList(PolitaireParser.politaireList)))
  }

  def rules = withAdminSession { implicit request =>
    Future.successful(Ok(views.html.admin.rules.rulesList(GameRulesSet.all)))
  }

  def importRules = withAdminSession { implicit request =>
    Future.successful(Ok(views.html.admin.rules.rulesList(PolitaireParser.gameRules)))
  }

  def exportRules = withAdminSession { implicit request =>
    Future {
      val rulesSet = PolitaireParser.gameRules
      ScalaExporter.export(rulesSet)
      Redirect(routes.RulesController.rules())
    }
  }

  def links = withAdminSession { implicit request =>
    Future {
      val links = LinkParser.parse()
      val ret = "<ul>\n" + links.map(l => "\n  <li>" + l._1 + ": [" + l._2.mkString(" :: ") + "]</li>").mkString("\n") + "\n</ul>"
      Ok(Html(ret))
    }
  }

  def wipeRules = withAdminSession { implicit request =>
    Future {
      RulesReset.go()
      Redirect(routes.RulesController.rules())
    }
  }
}
