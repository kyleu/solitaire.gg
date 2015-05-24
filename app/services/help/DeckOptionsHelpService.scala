package services.help

import models.game.rules.DeckOptions
import play.api.i18n.{Lang, Messages}
import utils.NumberUtils

object DeckOptionsHelpService {
  private[this] val defaultDeckOptions = DeckOptions()

  def deck(options: DeckOptions)(implicit lang: Lang) = {
    val numCards = options.numDecks * options.ranks.size * options.suits.size
    val plural = if (options.numDecks != 1) { "s" } else { "" }

    if (options.suits == defaultDeckOptions.suits) {
      if (options.ranks == defaultDeckOptions.ranks) {
        if (options.numDecks == 1) {
          Messages("help.deck.single.standard", numCards)
        } else {
          Messages("help.deck.multiple.standard", numCards, NumberUtils.toWords(options.numDecks, properCase = true))
        }
      } else {
        val ranks = options.ranks.mkString(", ")
        if (options.numDecks == 1) {
          Messages("help.deck.single.oddranks", numCards, ranks)
        } else {
          Messages("help.deck.multiple.oddranks", NumberUtils.toWords(options.numDecks, properCase = true), numCards, ranks)
        }
      }
    } else {
      val suits = options.suits.mkString(", ")
      if (options.ranks == defaultDeckOptions.ranks) {
        if (options.numDecks == 1) {
          Messages("help.deck.single.oddsuits", numCards, suits)
        } else {
          Messages("help.deck.multiple.oddsuits", NumberUtils.toWords(options.numDecks, properCase = true), numCards, suits)
        }
      } else {
        val ranks = options.ranks.mkString(", ")
        if (options.numDecks == 1) {
          Messages("help.deck.single.oddranksandsuits", numCards, ranks, suits)
        } else {
          Messages("help.deck.multiple.oddranksandsuits", NumberUtils.toWords(options.numDecks, properCase = true), numCards, ranks, suits)
        }
      }
    }
  }
}
