package services.wiki.template

import models.rules.{GameRules, GameRulesSet}
import services.wiki.WikiService.messages

class WikiRules(r: GameRules) {
  private[this] val ret = new StringBuilder()
  private[this] def l(s: String = ""): Unit = ret.append(s + "\n")

  private[this] def rulesLink(id: String, name: String) = {
    s"[$name]($id)"
  }

  def getTemplate = {
    l("# " + r.title)
    l()
    processDescription(r)
    l()
    processAka()
    processObjective()
    processDeck()
    processLayout()
    processSimilar()
    processRelated()
    processLinks()

    ret.toString
  }

  private[this] val descriptionLinkPattern = """\^([a-z0-9]+)\^""".r

  private[this] def processDescription(rules: GameRules, link: Boolean = true) = {
    val desc = messages(s"rules.${rules.id}.description")
    val links = descriptionLinkPattern.findAllIn(desc).matchData.map(_.group(1))
    val linked = links.foldLeft(desc) { (desc, id) =>
      val rules = GameRulesSet.allByIdWithAliases(id)
      if (link) {
        desc.replaceAllLiterally(s"^$id^", s"""[${rules.title}](${rules.id})""")
      } else {
        desc.replaceAllLiterally(s"^$id^", rules.title)
      }
    }
    l(linked)
  }

  private[this] def processAka() = if (r.aka.nonEmpty) {
    l("## " + messages("help.also.known.as"))
    l()
    r.aka.toSeq.foreach { aka =>
      l(rulesLink(aka._1, aka._2))
    }
    l()
  }

  private[this] def processObjective() = {
    l("## " + messages("help.objective"))
    l()
    l(WikiObjective.objective(r))
    l()
  }

  private[this] def processDeck() = {
    l("## " + messages("help.deck"))
    l()
    val deckStuff = WikiDeckOptions.deck(r.deckOptions)
    if (deckStuff.nonEmpty) {
      deckStuff.foreach(l)
      l()
    }
  }

  private[this] def processLayout() = {
    l("## " + messages("help.layout"))
    l()
    WikiLayout.layout(r).foreach { pileSet =>
      l("### " + pileSet._1)
      l()
      pileSet._2.foreach { statement =>
        l(statement)
      }
      l()
    }
  }

  private[this] def processSimilar() = r.like.foreach { _ =>
    val like = models.rules.GameRulesSet.allByIdWithAliases(r.id)
    l("## " + messages("help.original.game"))
    l()
    l(rulesLink(like.id, like.title))
    l(messages(s"rules.${r.id}.description"))
    processDescription(like, link = false)
    l()
  }

  private[this] def processRelated() = {
    val rels = GameRulesSet.allByIdWithAliases.filter(x => r.related.contains(x._2.id)).toSeq
    if (rels.nonEmpty) {
      l("## " + messages("help.related.games"))
      l()
      rels.foreach { rel =>
        l(rulesLink(rel._1, rel._2.title))
        processDescription(rel._2, link = false)
        l()
      }
    }
  }

  private[this] def processLinks() = if (r.links.nonEmpty) {
    l("## " + messages("help.web.resources"))
    l()
    r.links.foreach { link =>
      l(s"[${link.title}](${link.url})")
      l()
    }
  }
}
