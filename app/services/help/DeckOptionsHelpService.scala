package services.help

import models.game.rules.DeckOptions
import utils.NumberUtils

object DeckOptionsHelpService {
  private[this] val defaultDeckOptions = DeckOptions()

  def deck(options: DeckOptions) = {
    val numCards = options.numDecks * options.ranks.size * options.suits.size
    val plural = if(options.numDecks != 1) { "s" } else { "" }
    val prefix = NumberUtils.toWords(options.numDecks, properCase = true)

    if(options.suits.sameElements(defaultDeckOptions.suits)) {
      if(options.ranks.sameElements(defaultDeckOptions.ranks)) {
        prefix + " standard deck" + plural + " of " + numCards + " cards."
      } else {
        val ranks = options.ranks.mkString(", ")
        prefix + " deck" + plural + " of " + numCards + " cards using ranks " + ranks + "."
      }
    } else {
      val suits = options.ranks.mkString(", ")
      if(options.ranks.sameElements(defaultDeckOptions.ranks)) {
        prefix + " deck" + plural + " of " + numCards + " cards using suits " + suits + "."
      } else {
        val ranks = options.ranks.mkString(", ")
        prefix + " deck" + plural + " of " + numCards + " cards using suits " + suits + " and ranks " + ranks + "."
      }
    }
  }
}
