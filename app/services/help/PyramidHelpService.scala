package services.help

import models.game.rules._
import play.api.i18n.{Messages, Lang}
import utils.NumberUtils

object PyramidHelpService {
  private[this] val defaults = PyramidRules()

  def pyramid(rules: PyramidRules, deckOptions: DeckOptions)(implicit lang: Lang) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val rows = rules.height match {
      case 1 => Messages("help.pyramid.rows.single")
      case x => Messages("help.pyramid.rows.multiple", x)
    }

    rules.pyramidType match {
      case PyramidType.Standard => ret += Messages("help.pyramid.type.standard", rows)
      case PyramidType.Inverted => ret += Messages("help.pyramid.type.inverted", rows)
    }

    if (rules.wrapFromKingToAce) {
      ret += Messages("help.pyramid.wrap.ranks")
    }

    if(rules.rankMatchRuleForBuilding != RankMatchRule.None && rules.suitMatchRuleForBuilding != SuitMatchRule.None) {
      ret += Messages("help.pyramid.build.none")
    } else {
      ret += Messages(
        "help.pyramid.build.rank.and.suit.match.rules",
        MatchRuleHelpService.toWords(rules.rankMatchRuleForBuilding),
        MatchRuleHelpService.toWords(rules.suitMatchRuleForBuilding)
      )
    }

    if(rules.rankMatchRuleForMovingStacks != RankMatchRule.None && rules.suitMatchRuleForMovingStacks != SuitMatchRule.None) {
      ret += Messages("help.pyramid.move.stacks.none")
    } else {
      ret += Messages(
        "help.pyramid.move.stacks.rank.and.suit.match.rules",
        MatchRuleHelpService.toWords(rules.rankMatchRuleForBuilding),
        MatchRuleHelpService.toWords(rules.suitMatchRuleForBuilding)
      )
    }

    ret += MatchRuleHelpService.toWords(rules.emptyFilledWith, deckOptions.lowRank, deckOptions.highRank)

    val name = if (rules.setNumber == 0) {
      rules.name
    } else {
      if (rules.name == defaults.name) {
        rules.name + " " + NumberUtils.toWords(rules.setNumber + 1, properCase = true)
      } else {
        rules.name
      }
    }

    name -> ret.toSeq
  }
}
