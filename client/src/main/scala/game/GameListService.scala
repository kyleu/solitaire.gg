package game

import models.rules.{GameRules, GameRulesSet}

import scalatags.Text.all._
import org.scalajs.jquery.{jQuery => $}
import utils.TemplateUtils

object GameListService {
  private[this] var initialized = false

  private[this] def rulesLink(k: String, v: GameRules) = a(href := s"/play/$k", cls := "rules-link", data("rules") := k)(v.title)

  private[this] val descriptionLinkPattern = """\^([a-z0-9]+)\^""".r

  private[this] def rulesDescription(desc: String, link: Boolean = true) = {
    val links = descriptionLinkPattern.findAllIn(desc).matchData.map(_.group(1))
    val linked = links.foldLeft(desc) { (desc, id) =>
      val rules = GameRulesSet.allByIdWithAliases(id)
      if (link) {
        val url = s"/play/${rules.id}"
        desc.replaceAllLiterally(s"^$id^", rulesLink(id, rules).toString)
      } else {
        desc.replaceAllLiterally(s"^$id^", rules.title)
      }
    }
    linked
  }

  def initIfNeeded(onNewGame: Seq[String] => Unit) = if (!initialized) {
    val content = div(
      h4("Available Games"),
      table(tbody(GameRulesSet.completed.map { rules =>
        tr(
          td(rulesLink(rules._1, rules._2)),
          td(raw(rulesDescription(rules._2.description)))
        )
      }))
    )

    val panel = $("#panel-list-games")
    panel.html(content.toString)

    TemplateUtils.clickHandler($(".rules-link", panel), jq => onNewGame(Seq(jq.data("rules").toString)))

    initialized = true
  }
}
