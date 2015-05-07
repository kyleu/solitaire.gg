package services.help

import models.game.Rank
import models.game.rules.{ DeckOptions, FoundationLowRank, FoundationRules }
import utils.NumberUtils

object FoundationHelpService {
  def foundation(rules: FoundationRules, deckOptions: DeckOptions) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val piles = rules.numPiles match {
      case 1 => if (rules.initialCards == 0) {
        "A single empty foundation pile."
      } else {
        val plural = if (rules.initialCards == 1) { "" } else { "s" }
        "A single foundation pile with " + rules.initialCards + " initial card" + plural + "."
      }
      case x =>
        NumberUtils.toWords(x, properCase = true) + (if (rules.initialCards == 0) {
          " empty foundation piles."
        } else if (rules.initialCards % rules.numPiles == 0) {
          val plural = if (rules.initialCards / rules.numPiles == 1) { "" } else { "s" }
          " foundation piles with " + NumberUtils.toWords(rules.initialCards / rules.numPiles) + " initial card" + plural + " dealt to each."
        } else {
          val plural = if (rules.initialCards == 1) { "" } else { "s" }
          " foundation piles with " + rules.initialCards + " initial card" + plural + "."
        })
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
      ret += "Any card may be moved to any empty foundation pile."
    } else if (lowRank == Rank.Unknown && rules.lowRank == FoundationLowRank.Ascending) {
      ret += "There is one foundation pile for each rank from Ace to King."
    } else if (lowRank == Rank.Unknown) {
      ret += "The first card played to a foundation becomes the base card for others."
    } else {
      ret += "Any " + lowRank + " may be played to any empty foundation pile."
    }

    ret += "A card may be played to a foundation pile if it is " + rules.rankMatchRule.toWords + " and " + rules.suitMatchRule.toWords + "."

    if (rules.wrapFromKingToAce) {
      ret += "An Ace may be played on a King, continuing the sequence."
    }

    if (rules.cardsShown > 1) {
      ret += NumberUtils.toWords(rules.cardsShown, properCase = true) + " cards are visible."
    }

    rules.name -> ret.toSeq
  }
}
