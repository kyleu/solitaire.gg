package help

import menu.MenuService
import models.rules.GameRulesSet
import org.scalajs.jquery.{jQuery => $}

object HelpService {
  def show(rules: Option[String], menu: MenuService) = rules match {
    case Some(rulesId) => showRules(rulesId, menu)
    case None => showGeneral(menu)
  }

  private[this] def showRules(rulesId: String, menu: MenuService) = {
    val rules = GameRulesSet.allByIdWithAliases(rulesId)
    val html = HelpTemplate.help(rules)
    menu.options.setOptionsForHelp(Some(rulesId))
    $("#help-content").html(html.toString)
  }

  private[this] def showGeneral(menu: MenuService) = {
    menu.options.setOptionsForHelp(None)
    $("#help-content").html(HelpTemplate.generalHelp.toString)
  }
}
