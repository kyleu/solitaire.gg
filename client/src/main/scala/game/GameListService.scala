package game

import models.rules.GameRulesSet

import scalatags.Text.all._
import org.scalajs.jquery.{jQuery => $}

object GameListService {
  private[this] var initialized = false

  def initIfNeeded() = if (!initialized) {
    val content = div(
      h4("Available Games"),
      table(tbody(GameRulesSet.completed.map { rules =>
        tr(
          td(rules._2.title),
          td(
            div(rules._2.description)
          )
        )
      }))
    )

    val panel = $("#panel-list-games")
    panel.html(content.toString)

    initialized = true
  }
}
