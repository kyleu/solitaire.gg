package game

import models.rules.GameRulesSet

import scalatags.Text.all._
import org.scalajs.jquery.{jQuery => $}

object GameListService {
  private[this] var initialized = false

  def initIfNeeded() = if (!initialized) {
    val content = div(
      h4("Available Games"),
      ul(GameRulesSet.completed.map { rules =>
        li(rules._1)
      })
    )

    $("#panel-list-games").html(content.toString)

    initialized = true
  }

}
