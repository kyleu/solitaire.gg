package services.help

import models.game.rules.GameRules

object DynamicHelpService {
  def helpFor(rules: GameRules) = {
    Map(
      "pre-deal" -> "The game is played with a single, standard deck of 52 playing cards. The cards are shuffled and placed face-down in the stock.",
      "post-deal" -> "Cards are then dealt to the tableau in seven piles of increasing size.",

      "objective" -> "To win a game of Klondike, build the four foundation piles up in sequence, from Ace to King, all with the same suit.",

      "select-pile-stock" -> "Once you've used all the cards in the stock, click the empty pile to fill it from the waste.",
      "only-stock-available" -> "Touch or click the stock to deal three cards to the waste. The top card of the waste pile can be played.",
      "only-stock-available" -> "When no more moves are available, touch or click the stock to deal three new cards.",

      "move-to-empty-foundation" -> "An Ace can be moved to an empty foundation.",
      "move-to-full-foundation" -> "You may move a card to the foundation if it is the same suit and one rank higher.",

      "move-single-to-empty-tableau" -> "Kings can be moved to empty tableau piles, starting a new sequence.",
      "move-single-to-full-tableau" -> "Single face-up cards can be moved to another tableau pile if its top card is one rank lower and a different color.",
      "move-sequence-to-full-tableau" -> "Sequences of face-up cards can be moved to another tableau pile if its contimues the sequence.",
      "select-card-tableau" -> "Touch or click a face-down card to turn it over.",

      "move-to-pyramid" -> "Only uncovered cards can be played from the pyramid. You can remove cards by making pairs totalling 13.",
      "select-card-pyramid" -> "Only uncovered cards can be played from the pyramid. You can remove cards by making pairs totalling 13.",

      "no-moves-available" -> "No more moves are available. You have lost. Sorry about that."
    )
  }
}
