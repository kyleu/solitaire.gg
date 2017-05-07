package game

import models.rules.GameRulesSet

import scalatags.Text.all._
import org.scalajs.jquery.{jQuery => $}
import utils.TemplateUtils

object GameListService {
  private[this] var initialized = false

  def initIfNeeded(onNewGame: String => Unit) = if (!initialized) {
    val content = div(
      h4("Available Games"),
      table(tbody(GameRulesSet.completed.map { rules =>
        tr(
          td(a(href := s"/play/${rules._1}", cls := "rules-link", data("rules") := rules._1)(rules._2.title)),
          td(
            div(rules._2.description)
          )
        )
      }))
    )

    val panel = $("#panel-list-games")
    panel.html(content.toString)

    TemplateUtils.clickHandler($(".rules-link", panel), jq => onNewGame(jq.data("rules").toString))

    initialized = true
  }
}
