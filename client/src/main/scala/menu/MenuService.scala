package menu

import models.settings.MenuPosition
import navigation.NavigationService
import org.scalajs.jquery.{jQuery => $}
import util.TemplateUtils

object MenuService {
  case class Entry(id: String, url: Seq[String], title: String, onClick: () => Unit) {
    lazy val asHtml = s"""<a id="menu-link-$id" href="${NavigationService.assetRoot + url.mkString("/")}">$title</a>"""
  }
}

class MenuService(val navigation: NavigationService) {
  def rulesHelpEntry(rules: String) = MenuService.Entry("help", Seq("help", rules), "Help", () => navigation.rulesHelp(rules))
  val generalHelpEntry = MenuService.Entry("help", Seq("help"), "Help", () => navigation.generalHelp())
  val settingsEntry = MenuService.Entry("settings", Seq("settings"), "Settings", () => navigation.settings())

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

  private[this] val options = $("#nav-options")
  if (options.length != 1) {
    throw new IllegalStateException(s"Found [${options.length}] menu options elements.")
  }

  def toggleMenu() = navigation.menu()

  def setTitle(t: String) = title.text(t)

  def setOptions(entries: MenuService.Entry*) = {
    options.html(entries.map(x => x.asHtml).mkString("\n"))
    entries.foreach(e => TemplateUtils.clickHandler($(s"#menu-link-${e.id}", options), jq => e.onClick()))
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
