package help

import models.card.Rank
import models.rules.DeckOptions
import util.Messages

object DeckOptionsHelpService {
  private[this] val defaultDeckOptions = DeckOptions()

  def deck(options: DeckOptions) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]
    val numCards = options.numDecks * options.ranks.size * options.suits.size

    if (options.suits == defaultDeckOptions.suits) {
      if (options.ranks == defaultDeckOptions.ranks) {
        if (options.numDecks == 1) {
          ret += Messages("help.deck.single.standard", numCards)
        } else {
          ret += Messages("help.deck.multiple.standard", Messages.numberAsString(options.numDecks, properCase = true), numCards)
        }
      } else {
        val ranks = options.ranks.mkString(", ")
        if (options.numDecks == 1) {
          ret += Messages("help.deck.single.oddranks", numCards, ranks)
        } else {
          ret += Messages("help.deck.multiple.oddranks", Messages.numberAsString(options.numDecks, properCase = true), numCards, ranks)
        }
      }
    } else {
      val suits = options.suits.mkString(", ")
      if (options.ranks == defaultDeckOptions.ranks) {
        if (options.numDecks == 1) {
          ret += Messages("help.deck.single.oddsuits", numCards, suits)
        } else {
          ret += Messages("help.deck.multiple.oddsuits", Messages.numberAsString(options.numDecks, properCase = true), numCards, suits)
        }
      } else {
        val ranks = options.ranks.mkString(", ")
        if (options.numDecks == 1) {
          ret += Messages("help.deck.single.oddranksandsuits", numCards, ranks, suits)
        } else {
          ret += Messages("help.deck.multiple.oddranksandsuits", Messages.numberAsString(options.numDecks, properCase = true), numCards, ranks, suits)
        }
      }
    }

    if (options.lowRank != Rank.Ace) {
      ret += s"${options.lowRank} is the lowest card."
    }

    ret
  }
}
