package game

import models.rules.{GameRules, GameRulesSet}
import navigation.NavigationUrls
import utils.Messages

import scalatags.Text.all._

object GameListTemplate {
  def list() = div(cls := "theme striped with-margin")(
    table(cls := "game-list-table")(
      thead(tr(th("Title"), th(""), th(""))),
      tbody(GameRulesSet.completed.flatMap { rules =>
        Seq(
          tr(cls := "title-row")(
            td(rulesLink(rules._1, rules._2)),
            td(a(href := NavigationUrls.rules(rules._1), cls := "help-link", data("rules") := rules._1)("How To Play")),
            td(a(href := NavigationUrls.play(rules._1), cls := "rules-link", data("rules") := rules._1)("Play Now"))
          ),
          tr(
            td(colspan := 3)(div(cls := "description")(em(raw(rulesDescription(rules._2.id)))))
          )
        )
      })
    )
  )

  private[this] def rulesLink(k: String, v: GameRules) = a(href := NavigationUrls.play(k), cls := "rules-link", data("rules") := k)(v.title)

  private[this] val descriptionLinkPattern = """\^([a-z0-9]+)\^""".r

  private[this] def rulesDescription(id: String, link: Boolean = true) = {
    val desc = Messages(s"rules.$id.description")
    val links = descriptionLinkPattern.findAllIn(desc).matchData.map(_.group(1))
    val linked = links.foldLeft(desc) { (desc, id) =>
      val rules = GameRulesSet.allByIdWithAliases(id)
      if (link) {
        desc.replaceAllLiterally(s"^$id^", rulesLink(id, rules).toString)
      } else {
        desc.replaceAllLiterally(s"^$id^", rules.title)
      }
    }
    linked
  }
}
