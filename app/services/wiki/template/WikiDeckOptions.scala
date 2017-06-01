package services.wiki.template

import models.card.Rank
import models.rules.DeckOptions
import services.wiki.WikiService
import services.wiki.WikiService.messages

object WikiDeckOptions {
  private[this] val defaultDeckOptions = DeckOptions()

  def deck(options: DeckOptions) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]
    val numCards = options.numDecks * options.ranks.size * options.suits.size

    if (options.suits == defaultDeckOptions.suits) {
      if (options.ranks == defaultDeckOptions.ranks) {
        if (options.numDecks == 1) {
          ret += messages("help.deck.single.standard", numCards)
        } else {
          ret += messages("help.deck.multiple.standard", WikiService.numberAsString(options.numDecks, properCase = true), numCards)
        }
      } else {
        val ranks = options.ranks.mkString(", ")
        if (options.numDecks == 1) {
          ret += messages("help.deck.single.oddranks", numCards, ranks)
        } else {
          ret += messages("help.deck.multiple.oddranks", WikiService.numberAsString(options.numDecks, properCase = true), numCards, ranks)
        }
      }
    } else {
      val suits = options.suits.mkString(", ")
      if (options.ranks == defaultDeckOptions.ranks) {
        if (options.numDecks == 1) {
          ret += messages("help.deck.single.oddsuits", numCards, suits)
        } else {
          ret += messages("help.deck.multiple.oddsuits", WikiService.numberAsString(options.numDecks, properCase = true), numCards, suits)
        }
      } else {
        val ranks = options.ranks.mkString(", ")
        if (options.numDecks == 1) {
          ret += messages("help.deck.single.oddranksandsuits", numCards, ranks, suits)
        } else {
          ret += messages("help.deck.multiple.oddranksandsuits", WikiService.numberAsString(options.numDecks, properCase = true), numCards, ranks, suits)
        }
      }
    }

    if (options.lowRank != Rank.Ace) {
      ret += s"${options.lowRank} is the lowest card."
    }

    ret
  }
}
