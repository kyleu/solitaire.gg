package models.game.pile

import models.game.pile.actions.SelectCardActions
import models.game.{GameState, Card}

class Waste(override val id: String) extends Pile(id, "waste") {
  override def canSelectCard(card: Card) = this.cards.lastOption == Some(card)
  override def canDragFrom(cards: Seq[Card]) = cards.length == 1 && Some(cards(0)) == this.cards.lastOption

  override def onSelectCard(card: Card, gameState: GameState) = SelectCardActions.playToFoundation(this, card, gameState)
}
