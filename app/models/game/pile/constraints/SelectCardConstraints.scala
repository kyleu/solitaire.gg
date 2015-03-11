package models.game.pile.constraints

import models.game.Card
import models.game.pile.Pile

object SelectCardConstraints {
  def isTopCard(pile: Pile, card: Card) = pile.cards.lastOption == Some(card)
}
