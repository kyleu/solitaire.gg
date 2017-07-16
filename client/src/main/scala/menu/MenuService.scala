package menu

import models.settings.MenuPosition
import navigation.{NavigationService, NavigationState}
import org.scalajs.jquery.{jQuery => $}
import settings.SettingsService
import util.TemplateUtils

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
    if (jq.length == 0) { None } else { Some(s -> jq) }
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

  private[this] val delay = 500
  private[this] var menuPosition: MenuPosition = MenuPosition.Hidden

  def setMenuPosition(pos: MenuPosition) = {
    val b = $("body")
    val m = $("#menu-nav")
    pos match {
      case _ if pos == menuPosition => // noop
      case MenuPosition.Top =>
        b.removeClass("bottom-nav").addClass("top-nav")
        if (menuPosition == MenuPosition.Hidden) { m.fadeIn(delay) }
      case MenuPosition.Bottom =>
        b.removeClass("top-nav").addClass("bottom-nav")
        if (menuPosition == MenuPosition.Hidden) { m.fadeIn(delay) }
      case MenuPosition.Hidden => if (menuPosition != MenuPosition.Hidden) {
        m.fadeOut(delay)
      }
      case _ => throw new IllegalStateException(s"Unhandled menu position [$pos].")
    }
    menuPosition = pos
  }
}
