package game

import models.rules.{GameRules, GameRulesSet}
import navigation.NavigationUrls
import utils.Messages

import scalatags.Text.all._

object GameListTemplate {
  def panelContent = div(
    div(cls := "theme striped with-margin options-el")("Solitaire.gg"),
    list("Favorite Games", GameRulesSet.favorites.map(x => x.id -> x)),
    list("All Games", GameRulesSet.completed)
  )

  def optionsContent(ag: ActiveGame) = div(
    ul(cls := "game-links")(
      li(a(cls := "rules-link", href := s"/play/${ag.rulesId}", data("rules") := ag.rulesId)(Messages("options.resign"))),
      li(a(cls := "rules-link", href := s"/play/${ag.rulesId}", data("rules") := ag.rulesId)(Messages("options.redeal"))),
      li(a(cls := "rules-link", href := s"/play/${ag.rulesId}", data("rules") := ag.rulesId)(Messages("options.same.deal"))),
      li(a(cls := "rules-link", href := s"/play/${ag.rulesId}", data("rules") := ag.rulesId)(Messages("options.winnable")))
    )
  )

  private[this] def list(title: String, ruleSet: Seq[(String, GameRules)]) = div(cls := "theme striped with-margin")(
    div(title),
    ul(cls := "game-links")(ruleSet.map { r =>
      li(a(cls := "rules-link", href := s"/play/${r._1}", data("rules") := r._1)(r._2.title))
    })
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
