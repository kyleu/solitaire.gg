package game

import help.HelpService
import org.scalajs.jquery.{jQuery => $}
import utils.TemplateUtils

object GameListService {
  private[this] var initialized = false

  def initIfNeeded(onNewGame: Seq[String] => Unit) = if (!initialized) {
    val content = GameListTemplate.panelContent()

    val panel = $("#panel-list-games .content")
    panel.html(content.toString)

    TemplateUtils.clickHandler($(".rules-link", panel), jq => onNewGame(Seq(jq.data("rules").toString)))
    TemplateUtils.clickHandler($(".help-link", panel), jq => HelpService.show(Some(jq.data("rules").toString)))

    initialized = true
  }
}
