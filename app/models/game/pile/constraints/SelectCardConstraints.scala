package models.game.pile.constraints

import models.game.Card
import models.game.pile.Pile

case class SelectCardConstraint(id: String, f: (Pile, Card) => Boolean)

object SelectCardConstraints {
  val never = SelectCardConstraint("never", (pile, card) => false)

  val topCardOnly = SelectCardConstraint("top-card-only", (pile, card) => pile.cards.lastOption == Some(card))
}
