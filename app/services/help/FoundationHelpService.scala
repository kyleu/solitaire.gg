package services.help

import models.game.Rank
import models.game.rules._
import play.api.i18n.{Lang, Messages}
import utils.NumberUtils

object FoundationHelpService {
  private[this] val defaults = FoundationRules()

  def foundation(rules: FoundationRules, deckOptions: DeckOptions)(implicit lang: Lang) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val piles = rules.numPiles match {
      case 1 => if (rules.initialCards == 0) {
        Messages("help.piles.single.cards.empty", rules.name.toLowerCase)
      } else if (rules.initialCards == 1) {
        Messages("help.piles.single.cards.single", rules.name.toLowerCase)
      } else {
        Messages("help.piles.single.cards.multiple", rules.name.toLowerCase, rules.initialCards)
      }
      case x =>
        if (rules.initialCards == 0) {
          Messages("help.piles.multiple.cards.empty", NumberUtils.toWords(x, properCase = true), rules.name.toLowerCase)
        } else if (rules.initialCards % rules.numPiles == 0) {
          if (rules.initialCards / rules.numPiles == 1) {
            Messages("help.piles.multiple.cards.single.each", NumberUtils.toWords(x, properCase = true), rules.name.toLowerCase)
          } else {
            val init = NumberUtils.toWords(rules.initialCards / rules.numPiles)
            Messages("help.piles.multiple.cards.multiple.each", NumberUtils.toWords(x, properCase = true), rules.name.toLowerCase, init)
          }
        } else {
          if (rules.initialCards == 1) {
            Messages("help.piles.multiple.cards.single", NumberUtils.toWords(x, properCase = true), rules.name.toLowerCase)
          } else {
            Messages(
              "help.piles.multiple.cards.multiple",
              NumberUtils.toWords(x, properCase = true),
              rules.name.toLowerCase,
              NumberUtils.toWords(rules.initialCards)
            )
          }
        }
    }
    ret += piles

    val lowRank = rules.lowRank match {
      case FoundationLowRank.AnyCard => Rank.Unknown
      case FoundationLowRank.Ascending => Rank.Unknown
      case FoundationLowRank.DeckHighRank => deckOptions.highRank
      case FoundationLowRank.DeckLowRank => deckOptions.lowRank
      case FoundationLowRank.SpecificRank(r) => r
    }

    if (lowRank == Rank.Unknown && rules.lowRank == FoundationLowRank.AnyCard) {
      ret += Messages("help.foundation.lowrank.any")
    } else if (lowRank == Rank.Unknown && rules.lowRank == FoundationLowRank.Ascending) {
      ret += Messages("help.foundation.lowrank.ascending")
    } else if (lowRank == Rank.Unknown) {
      ret += Messages("help.foundation.lowrank.first.becomes.base")
    } else {
      ret += Messages("help.foundation.lowrank.specific", lowRank)
    }

    rules.initialCardRestriction match {
      case Some(FoundationInitialCardRestriction.SpecificColorUniqueSuits(c)) => ret += Messages(
        "help.foundation.initial.restriction.specific.color.unique.suits", c.toString.toLowerCase
      )
      case Some(FoundationInitialCardRestriction.SpecificSuit(s)) => ret += Messages("help.foundation.initial.restriction.specific.suit", s.toString)
      case Some(FoundationInitialCardRestriction.UniqueColors) => ret += Messages("help.foundation.initial.restriction.unique.colors")
      case Some(FoundationInitialCardRestriction.UniqueSuits) => ret += Messages("help.foundation.initial.restriction.unique.suits")
      case None => // no op
    }

    if(rules.rankMatchRule != RankMatchRule.None && rules.suitMatchRule != SuitMatchRule.None) {
      ret += Messages("help.foundation.build.none")
    } else {
      ret += Messages(
        "help.foundation.build.rank.and.suit.match.rules",
        MatchRuleHelpService.toWords(rules.rankMatchRule),
        MatchRuleHelpService.toWords(rules.suitMatchRule)
      )
    }

    if (rules.wrapFromKingToAce) {
      ret += Messages("help.foundation.wrap.ranks")
    }

    if (rules.cardsShown > 1) {
      ret += Messages("help.foundation.cards.shown", NumberUtils.toWords(rules.cardsShown, properCase = true))
    }

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
