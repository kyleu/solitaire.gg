package help

import models.rules.GameRules
import navigation.NavigationUrls
import util.Messages

import scalatags.Text.all._

object HelpTemplateContent {
  def rulesLink(rulesId: String, rulesTitle: String) = div(
    a(cls := "help-link", href := NavigationUrls.rules(rulesId))(rulesTitle),
    a(cls := "play-link", href := NavigationUrls.play(rulesId), data("rules") := rulesId, title := s"Play $rulesTitle Now")("play")
  )

  def getTitleDiv(rules: GameRules) = div(cls := "rules-help")(h2(
    rules.title,
    a(cls := "play-link", href := NavigationUrls.play(rules.id), title := Messages("help.play.now"))("play")
  ))

  def getLayoutDiv(rules: GameRules) = div(
    h3(Messages("help.layout")),
    ul(cls := "layout")(
      GameRulesHelpService.layout(rules).map { pileSet =>
        li(
          h4(pileSet._1),
          if (pileSet._2.nonEmpty) {
            ul(pileSet._2.map { statement =>
              li(statement)
            })
          } else {
            div()
          }
        )
      }
    )
  )

  def getSimilarDiv(rules: GameRules) = rules.like match {
    case Some(likeId) =>
      val like = models.rules.GameRulesSet.allByIdWithAliases(likeId)
      div(
        h3(Messages("help.original.game")),
        ul(
          li(
            rulesLink(like.id, like.title),
            div(
              em(GameRulesHelpService.description(like.id, link = false))
            )
          )
        )
      )
    case None => div()
  }

  def getLinksDiv(rules: GameRules) = if (rules.links.nonEmpty) {
    div(
      h3(Messages("help.web.resources")),
      ul(rules.links.map { l =>
        li(a(target := "_blank", href := l.url)(l.title))
      })
    )
  } else {
    div()
  }

  def getRelDiv(rules: GameRules) = {
    val rels = models.rules.GameRulesSet.completed.filter(x => rules.related.contains(x._2.id))
    if (rels.nonEmpty) {
      div(
        h3(Messages("help.related.games")),
        ul(rels.map { rel =>
          li(
            HelpTemplateContent.rulesLink(rel._1, rel._2.title),
            div(em(GameRulesHelpService.description(rel._2.id, link = false)))
          )
        })
      )
    } else {
      div()
    }
  }

  def getAkaDiv(rules: GameRules) = if (rules.aka.nonEmpty) {
    div(
      h3(Messages("help.also.known.as")),
      div(cls := "objective")(rules.aka.toSeq.map { aka =>
        HelpTemplateContent.rulesLink(aka._1, aka._2)
      })
    )
  } else {
    div()
  }
}
