package services.wiki.template

import models.rules._
import services.wiki.WikiService
import services.wiki.WikiService.messages

object WikiPyramid {
  private[this] val defaults = PyramidRules()

  def pyramid(rules: PyramidRules, deckOptions: DeckOptions) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]
    val loweredName = rules.name.toLowerCase

    val rows = rules.height match {
      case 1 => messages("help.pyramid.rows.single")
      case x => messages("help.pyramid.rows.multiple", x)
    }

    rules.pyramidType match {
      case PyramidType.Standard => ret += messages("help.pyramid.type.standard", loweredName, rows)
      case PyramidType.Inverted => ret += messages("help.pyramid.type.inverted", loweredName, rows)
    }

    if (rules.wrap) {
      ret += messages("help.pyramid.wrap.ranks")
    }

    if (rules.rankMatchRuleForBuilding == RankMatchRule.None || rules.suitMatchRuleForBuilding == SuitMatchRule.None) {
      ret += messages("help.pyramid.build.none", loweredName)
    } else {
      ret += messages(
        "help.pyramid.build.rank.and.suit.match.rules",
        loweredName,
        WikiMatchRule.toWords(rules.rankMatchRuleForBuilding),
        WikiMatchRule.toWords(rules.suitMatchRuleForBuilding)
      )
    }

    if (rules.rankMatchRuleForMovingStacks == RankMatchRule.None || rules.suitMatchRuleForMovingStacks == SuitMatchRule.None) {
      ret += messages("help.pyramid.move.stacks.none", loweredName)
    } else {
      ret += messages(
        "help.pyramid.move.stacks.rank.and.suit.match.rules",
        loweredName,
        WikiMatchRule.toWords(rules.rankMatchRuleForBuilding),
        WikiMatchRule.toWords(rules.suitMatchRuleForBuilding)
      )
    }

    ret += WikiMatchRule.toWords(rules.emptyFilledWith, deckOptions.lowRank, deckOptions.highRank)

    val name = if (rules.setNumber == 0) {
      rules.name
    } else {
      if (rules.name == defaults.name) {
        s"${rules.name} ${WikiService.numberAsString(rules.setNumber + 1, properCase = true)}"
      } else {
        rules.name
      }
    }

    name -> ret
  }
}
