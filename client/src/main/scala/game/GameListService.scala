package game

import org.scalajs.jquery.{jQuery => $}
import utils.TemplateUtils

object GameListService {
  private[this] var initialized = false

  def initIfNeeded(onNewGame: Seq[String] => Unit) = if (!initialized) {
    val content = GameListTemplate.list()

    val panel = $("#panel-list-games .content")
    panel.html(content.toString)

    TemplateUtils.clickHandler($(".rules-link", panel), jq => onNewGame(Seq(jq.data("rules").toString)))

    initialized = true
  }
}
