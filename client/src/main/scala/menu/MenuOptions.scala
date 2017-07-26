package menu

import game.ActiveGame
import navigation.NavigationService
import org.scalajs.jquery.{jQuery => $}
import util.TemplateUtils

object MenuOptions {
  case class Entry(id: String, url: Seq[String], title: String, onClick: () => Unit) {
    lazy val asHtml = s"""<a id="menu-link-$id" href="${NavigationService.assetRoot + url.mkString("/")}">$title</a>"""
  }
}

class MenuOptions(val navigation: NavigationService) {
  private[this] def rulesHelpEntry(rules: String) = MenuOptions.Entry("help", Seq("help", rules), "Help", () => navigation.rulesHelp(rules))
  private[this] val generalHelpEntry = MenuOptions.Entry("help", Seq("help"), "Help", () => navigation.generalHelp())
  private[this] val settingsEntry = MenuOptions.Entry("settings", Seq("settings"), "Settings", () => navigation.settings())
  private[this] val rulesListEntry = MenuOptions.Entry("new-game", Nil, "New Game", () => navigation.games())

  private[this] val options = $("#nav-options")
  if (options.length != 1) {
    throw new IllegalStateException(s"Found [${options.length}] menu options elements.")
  }

  private[this] var activeGame: Option[ActiveGame] = None
  def setActiveGame(ag: Option[ActiveGame]) = {
    activeGame = ag
  }

  private[this] def agLink = activeGame match {
    case Some(ag) => MenuOptions.Entry("resume", Seq("play", ag.rulesId, ag.seed.toString), "Resume", () => navigation.resume(ag))
    case None => rulesListEntry
  }

  def setOptionsForGameList() = activeGame match {
    case Some(ag) =>
      val resume = MenuOptions.Entry("resume", Seq("play", ag.rulesId, ag.seed.toString), "Resume", () => navigation.resume(ag))
      setOptions(resume, settingsEntry, generalHelpEntry)
    case None => setOptions(settingsEntry, generalHelpEntry)
  }

  def setOptionsForGame() = activeGame match {
    case Some(ag) => setOptions(rulesHelpEntry(ag.rulesId), settingsEntry)
    case None => throw new IllegalStateException("No active game.")
  }

  def setOptionsForSettings() = setOptions(agLink, generalHelpEntry)

  def setOptionsForHelp(rules: Option[String]) = rules match {
    case Some(r) => setOptions(agLink, generalHelpEntry, settingsEntry)
    case None => setOptions(agLink, settingsEntry)
  }

  private[this] def setOptions(entries: MenuOptions.Entry*) = {
    options.html(entries.map(x => x.asHtml).mkString("\n"))
    entries.foreach(e => TemplateUtils.clickHandler($(s"#menu-link-${e.id}", options), jq => e.onClick()))
  }
}
