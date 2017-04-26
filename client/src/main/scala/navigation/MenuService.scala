package navigation

import help.HelpTemplate
import models.rules.GameRulesSet
import settings.SettingsService
import org.scalajs.jquery.{JQueryEventObject, jQuery => $}
import utils.TemplateUtils

class MenuService(settings: SettingsService, navigation: NavigationService) {
  private[this] var menuShown = false
  private[this] var priorState: NavigationService.State = NavigationService.State.Loading
  private[this] val toggle = $("#menu-toggle")
  if (toggle.length != 1) {
    throw new IllegalStateException(s"Found [${toggle.length}] menu toggles.")
  }

  TemplateUtils.clickHandler($("#menu-link-help"), jq => {
    val rules = GameRulesSet.allByIdWithAliases("klondike")
    val html = HelpTemplate.help(rules)
    $("#help-content").html(html.toString)
    navigation.navigate(NavigationService.State.Help)
  })

  TemplateUtils.clickHandler($("#menu-link-play"), jq => {
    navigation.navigate(NavigationService.State.Game)
  })

  TemplateUtils.clickHandler(toggle, jq => {
    if (navigation.getState == NavigationService.State.Menu) {
      navigation.navigate(priorState)
    } else {
      priorState = navigation.getState
      navigation.navigate(NavigationService.State.Menu)
    }
  })
}
