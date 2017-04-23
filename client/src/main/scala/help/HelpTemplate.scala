package help

import models.rules.GameRules

import scalatags.Text.all._

object HelpTemplate {
  private[this] def rulesLink(rulesId: String, rulesTitle: String) = div(
    a(cls := "help-link", href := "")(rulesTitle),
    a(cls := "play-link", href := "", data("rules") := rulesId, title := s"Play $rulesTitle Now")("play")
  )

  def help(rules: GameRules) = {
    val titleDiv = div(cls := "rules-help")(h2(
      rules.title,
      a(cls := "play-link", href := "", title := "Messages(\"help.play.now\")")("play")
    ))

    val descriptionDiv = div(cls := "description")(em(GameRulesHelpService.description(rules.description)))

    val akaDiv = if (rules.aka.nonEmpty) {
      div(
        h3("@Messages(\"help.also.known.as\")"),
        div(cls := "objective")(rules.aka.toSeq.map { aka =>
          rulesLink(aka._1, aka._2)
        })
      )
    }

    val objectiveDiv = div(
      h3("@Messages(\"help.objective\")"),
      div(cls := "objective")(ObjectiveHelpService.objective(rules))
    )

    val deckDiv = div(
      h3("@Messages(\"help.deck\")"),
      div(cls := "deck")(DeckOptionsHelpService.deck(rules.deckOptions))
    )

    val layoutDiv = div(
      h3("@Messages(\"help.layout\")"),
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

    val similarDiv = {
      val like = models.rules.GameRulesSet.allByIdWithAliases(rules.id)
      rules.like match {
        case Some(l) => div(
          h3("@Messages(\"help.original.game\")"),
          ul(
            li(
              rulesLink(like.id, like.title),
              div(
                em(GameRulesHelpService.description(like.description, link = false))
              )
            )
          )
        )
        case None => div()
      }
    }

    val rels = models.rules.GameRulesSet.completed.filter(x => rules.related.contains(x._2.id))

    val relDiv = if (rels.nonEmpty) {
      div(
        h3("@Messages(\"help.related.games\")"),
        ul(rels.map { rel =>
          li(
            rulesLink(rel._1, rel._2.title),
            div(em(GameRulesHelpService.description(rel._2.description, link = false)))
          )
        })
      )
    } else {
      div()
    }

    val linksDiv = if (rules.links.nonEmpty) {
      div(
        h3("@Messages(\"help.web.resources\")"),
        ul(rules.links.map { l =>
          li(a(target := "_blank", href := "@routes.HomeController.externalLink(l.url)")(l.title))
        })
      )
    } else {
      div()
    }
  }
}
