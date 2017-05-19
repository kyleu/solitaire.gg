package help

import models.rules.GameRules

object DynamicHelpService {
  def helpFor(rules: GameRules) = {
    val ret = collection.mutable.ArrayBuffer.empty[(String, String)]

    val cardCount = rules.deckOptions.numDecks * rules.deckOptions.suits.size * rules.deckOptions.ranks.size

    if (rules.deckOptions.numDecks == 1) {
      ret += "pre-deal" -> s"The game is played with a single, standard deck of $cardCount playing cards."
    } else {
      ret += "pre-deal" -> s"The game is played with ${rules.deckOptions.numDecks} standard decks totalling $cardCount playing cards."
    }

    ret += "post-deal" -> "Cards are then dealt to the tableau in seven piles of increasing size."

    ret += "objective" -> "To win a game of Klondike, build the four foundation piles up in sequence, from Ace to King, all with the same suit."

    ret += "select-pile-stock" -> "Once you've used all the cards in the stock, click the empty pile to fill it from the waste."
    ret += "only-stock-available" -> "Touch or click the stock to deal three cards to the waste. The top card of the waste pile can be played."
    ret += "only-stock-available" -> "When no more moves are available, touch or click the stock to deal three new cards."

    ret += "move-to-empty-foundation" -> "An Ace can be moved to an empty foundation."
    ret += "move-to-full-foundation" -> "You may move a card to the foundation if it is the same suit and one rank higher."

    ret += "move-single-to-empty-tableau" -> "Kings can be moved to empty tableau piles, starting a new sequence."
    ret += "move-single-to-full-tableau" -> "Single face-up cards can be moved to another tableau pile if its top card is one rank lower and a different color."
    ret += "move-sequence-to-full-tableau" -> "Sequences of face-up cards can be moved to another tableau pile if it continues the sequence."
    ret += "select-card-tableau" -> "Touch or click a face-down card to turn it over."

    ret += "move-to-pyramid" -> "Only uncovered cards can be played from the pyramid. You can remove cards by making pairs totalling 13."
    ret += "select-card-pyramid" -> "Only uncovered cards can be played from the pyramid. You can remove cards by making pairs totalling 13."

    ret += "no-moves-available" -> "No more moves are available. You have lost. Sorry about that."

    ret.toIndexedSeq
  }
}
