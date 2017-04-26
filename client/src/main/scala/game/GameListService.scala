package game

import models.rules.GameRulesSet

import scalatags.Text.all._
import org.scalajs.jquery.{jQuery => $}

object GameListService {
  private[this] var initialized = false

  def initIfNeeded() = if (!initialized) {
    val content = div(
      h4("Available Games"),
      ul(cls := "collapsible")(GameRulesSet.completed.map { rules =>
        li(
          div(cls := "collapsible-header")(rules._2.title),
          div(cls := "collapsible-body")(
            div(rules._2.description)
          )
        )
      })
    )

    val panel = $("#panel-list-games")
    panel.html(content.toString)
    scalajs.js.Dynamic.global.$(".collapsible", panel).collapsible()

    initialized = true
  }
}
