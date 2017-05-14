package help

import models.rules.GameRulesSet
import org.scalajs.jquery.{jQuery => $}

object HelpService {
  def show(rules: Option[String]) = rules match {
    case Some(rulesId) => showRules(rulesId)
    case None => showGeneral()
  }

  private[this] def showRules(rulesId: String) = {
    val rules = GameRulesSet.allByIdWithAliases(rulesId)
    val html = HelpTemplate.help(rules)
    $("#help-content").html(html.toString)
  }

  private[this] def showGeneral() = {
    $("#help-content").html("General Help for Solitaire.gg")
  }
}
