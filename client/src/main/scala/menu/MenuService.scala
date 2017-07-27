package menu

import game.ActiveGame
import models.settings.MenuPosition
import navigation.NavigationService
import org.scalajs.jquery.{jQuery => $}
import util.TemplateUtils

class MenuService(val navigation: NavigationService) {
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

  def toggleMenu() = navigation.games()

  def setTitle(t: String) = title.text(t)

  val options = new MenuOptions(setTitle _, navigation)

  private[this] var activeGame: Option[ActiveGame] = None

  def setActiveGame(ag: Option[ActiveGame]) = {
    options.setActiveGame(ag)
    activeGame = ag
  }

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
