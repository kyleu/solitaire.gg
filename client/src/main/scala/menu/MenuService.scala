package menu

import navigation.{NavigationService, NavigationState}
import org.scalajs.jquery.{jQuery => $}
import settings.SettingsService
import utils.TemplateUtils

class MenuService(settings: SettingsService, navigation: NavigationService) {
  private[this] var priorState: NavigationState = NavigationState.Loading

  private[this] val title = $("#nav-title")
  if (title.length != 1) {
    throw new IllegalStateException(s"Found [${title.length}] menu titles.")
  }
  TemplateUtils.clickHandler(title, _ => toggleMenu())

  private[this] val toggle = $("#menu-toggle")
  if (toggle.length != 1) {
    throw new IllegalStateException(s"Found [${toggle.length}] menu toggles.")
  }
  TemplateUtils.clickHandler(toggle, _ => toggleMenu())

  private[this] val stateLinks = NavigationState.values.flatMap { s =>
    val jq = $(s"#menu-link-$s")
    TemplateUtils.clickHandler(jq, _ => navigation.navigate(s))
    if(jq.length == 0) { None } else { Some(s -> jq) }
  }.toMap

  def toggleMenu() = if (navigation.getState == NavigationState.Games) {
    navigation.navigate(priorState)
  } else {
    priorState = navigation.getState
    navigation.navigate(NavigationState.Games)
  }

  def setTitle(t: String) = title.text(t)

  def showAllLinks() = stateLinks.values.toSeq.map(_.show())
  def hideLink(s: NavigationState) = stateLinks.get(s).map(_.hide())
}
