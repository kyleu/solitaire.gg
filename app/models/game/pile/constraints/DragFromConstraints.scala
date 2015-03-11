package models.game.pile.constraints

import models.game.Card
import models.game.pile.Pile

object DragFromConstraints {
  def isSingleCard(pile: Pile, cards: Seq[Card]) = cards.length == 1
  def isTopCard(pile: Pile, cards: Seq[Card]) = cards.headOption == pile.cards.lastOption
}
