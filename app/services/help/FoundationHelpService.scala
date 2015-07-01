package services.help

import models.game.Rank
import models.rules._
import play.api.i18n.Messages
import utils.NumberUtils

object FoundationHelpService {
  private[this] val defaults = FoundationRules()

  def foundation(rules: FoundationRules, deckOptions: DeckOptions)(implicit messages: Messages) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]
    val loweredName = rules.name.toLowerCase

    ret += FoundationPilesHelpService.piles(rules)

    val lowRank = rules.lowRank match {
      case FoundationLowRank.AnyCard => Rank.Unknown
      case FoundationLowRank.Ascending => Rank.Unknown
      case FoundationLowRank.DeckHighRank => deckOptions.highRank
      case FoundationLowRank.DeckLowRank => deckOptions.lowRank
      case FoundationLowRank.SpecificRank(r) => r
    }

    if (rules.moveCompleteSequencesOnly) {
      ret += Messages("help.foundation.move.complete.sequences.only", loweredName)
    } else {
      if (lowRank == Rank.Unknown && rules.lowRank == FoundationLowRank.AnyCard) {
        ret += Messages("help.foundation.lowrank.any", loweredName)
      } else if (lowRank == Rank.Unknown && rules.lowRank == FoundationLowRank.Ascending) {
        ret += Messages("help.foundation.lowrank.ascending", loweredName)
      } else if (lowRank == Rank.Unknown) {
        ret += Messages("help.foundation.lowrank.first.becomes.base", loweredName)
      } else {
        ret += Messages("help.foundation.lowrank.specific", loweredName, lowRank)
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

      if (rules.rankMatchRule == RankMatchRule.None || rules.suitMatchRule == SuitMatchRule.None) {
        ret += Messages("help.foundation.build.none", loweredName)
      } else {
        ret += Messages(
          "help.foundation.build.rank.and.suit.match.rules",
          loweredName,
          MatchRuleHelpService.toWords(rules.rankMatchRule),
          MatchRuleHelpService.toWords(rules.suitMatchRule)
        )
      }

      if (rules.wrap) {
        val (lr, hr) = if (deckOptions.lowRank == Rank.Unknown) {
          Rank.Ace -> Rank.King
        } else {
          deckOptions.lowRank -> deckOptions.highRank
        }
        ret += Messages("help.foundation.wrap.ranks", lr, hr)
      }
    }

    if (rules.cardsShown > 1) {
      ret += Messages("help.foundation.cards.shown", NumberUtils.toWords(rules.cardsShown, properCase = true))
    }

    val name = if (rules.setNumber == 0) {
      rules.name
    } else {
      if (rules.name == defaults.name) {
        s"${rules.name} ${NumberUtils.toWords(rules.setNumber + 1, properCase = true)}"
      } else {
        rules.name
      }
    }

    name -> ret.toSeq
  }
}
