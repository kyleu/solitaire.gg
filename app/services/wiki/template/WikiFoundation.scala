package services.wiki.template

import models.card.Rank
import models.rules._

import services.wiki.WikiService
import services.wiki.WikiService.messages

object WikiFoundation {
  private[this] val defaults = FoundationRules()

  def foundation(rules: FoundationRules, deckOptions: DeckOptions) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]
    val loweredName = rules.name.toLowerCase

    ret += WikiFoundationPiles.piles(rules)

    val lowRank = rules.lowRank match {
      case FoundationLowRank.AnyCard => Rank.Unknown
      case FoundationLowRank.Ascending => Rank.Unknown
      case FoundationLowRank.DeckHighRank => deckOptions.highRank
      case FoundationLowRank.DeckLowRank => deckOptions.lowRank
      case FoundationLowRank.SpecificRank(r) => r
    }

    if (rules.moveCompleteSequencesOnly) {
      ret += messages("help.foundation.move.complete.sequences.only", loweredName)
    } else {
      if (lowRank == Rank.Unknown && rules.lowRank == FoundationLowRank.AnyCard) {
        ret += messages("help.foundation.lowrank.any", loweredName)
      } else if (lowRank == Rank.Unknown && rules.lowRank == FoundationLowRank.Ascending) {
        ret += messages("help.foundation.lowrank.ascending", loweredName)
      } else if (lowRank == Rank.Unknown) {
        ret += messages("help.foundation.lowrank.first.becomes.base", loweredName)
      } else {
        ret += messages("help.foundation.lowrank.specific", loweredName, lowRank)
      }

      rules.initialCardRestriction match {
        case Some(FoundationInitialCardRestriction.SpecificColorUniqueSuits(c)) => ret += messages(
          "help.foundation.initial.restriction.specific.color.unique.suits", c.toString.toLowerCase
        )
        case Some(FoundationInitialCardRestriction.SpecificSuit(s)) => ret += messages("help.foundation.initial.restriction.specific.suit", s.title)
        case Some(FoundationInitialCardRestriction.UniqueColors) => ret += messages("help.foundation.initial.restriction.unique.colors")
        case Some(FoundationInitialCardRestriction.UniqueSuits) => ret += messages("help.foundation.initial.restriction.unique.suits")
        case None => // no op
      }

      if (rules.rankMatchRule == RankMatchRule.None || rules.suitMatchRule == SuitMatchRule.None) {
        ret += messages("help.foundation.build.none", loweredName)
      } else {
        ret += messages(
          "help.foundation.build.rank.and.suit.match.rules",
          loweredName,
          WikiMatchRule.toWords(rules.rankMatchRule),
          WikiMatchRule.toWords(rules.suitMatchRule)
        )
      }

      if (rules.wrap) {
        val (lr, hr) = if (deckOptions.lowRank == Rank.Unknown) {
          Rank.Ace -> Rank.King
        } else {
          deckOptions.lowRank -> deckOptions.highRank
        }
        ret += messages("help.foundation.wrap.ranks", lr, hr)
      }
    }

    if (rules.cardsShown > 1) {
      ret += messages("help.foundation.cards.shown", WikiService.numberAsString(rules.cardsShown, properCase = true))
    }

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
