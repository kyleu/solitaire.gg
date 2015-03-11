package models.game.pile

import models.game.pile.actions.SelectCardActions
import models.game.pile.constraints.{DragFromConstraints, SelectCardConstraints}
import models.game.{GameState, Card}

class Waste(id: String, cardsShown: Option[Int]) extends Pile(id, "waste", cardsShown = cardsShown, direction = Some("r")) {
  override def canSelectCard(card: Card) = SelectCardConstraints.isTopCard(this, card)
  override def canDragFrom(cards: Seq[Card]) = DragFromConstraints.isSingleCard(this, cards) && DragFromConstraints.isTopCard(this, cards)

  override def onSelectCard(card: Card, gameState: GameState) = SelectCardActions.playToFoundation(this, card, gameState)
}
