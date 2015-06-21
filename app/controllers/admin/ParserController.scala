package controllers.admin

import controllers.BaseController
import models.game.rules.GameRulesSet
import parser.{ ScalaExporter, RulesReset }
import parser.politaire.{ LinkParser, PolitaireParser }
import play.api.i18n.MessagesApi
import play.twirl.api.Html

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

@javax.inject.Singleton
class ParserController @javax.inject.Inject() (override val messagesApi: MessagesApi) extends BaseController {
  def politaire = withAdminSession { implicit request =>
    Future.successful(Ok(views.html.admin.parser.politaireList(PolitaireParser.politaireList)))
  }

  def importRules = withAdminSession { implicit request =>
    Future.successful(Ok(views.html.admin.rules.rulesDataList(PolitaireParser.gameRules)))
  }

  def exportRules = withAdminSession { implicit request =>
    Future {
      val rulesSet = PolitaireParser.gameRules
      ScalaExporter.export(rulesSet)
      Redirect(routes.RulesController.rulesData())
    }
  }

  def links = withAdminSession { implicit request =>
    Future {
      val links = LinkParser.parse()
      val liSeq = links.map(l => s"\n  <li>${l._1}: [${l._2.mkString(" :: ")}]</li>").mkString("\n")
      val ret = s"<ul>\n$liSeq\n</ul>"
      Ok(Html(ret))
    }
  }

  def wipeRules = withAdminSession { implicit request =>
    Future {
      RulesReset.go()
      Redirect(routes.RulesController.rulesData())
    }
  }
}
