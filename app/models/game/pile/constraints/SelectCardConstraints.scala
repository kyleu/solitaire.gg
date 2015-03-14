package models.game.pile.constraints

import models.game.Card
import models.game.pile.{PileOption, Pile}

case class SelectCardConstraint(override val id: String, f: (Pile, Card) => Boolean) extends PileOption(id)

object SelectCardConstraints {
  val never = SelectCardConstraint("never", (pile, card) => false)

  val topCardOnly = SelectCardConstraint("top-card-only", (pile, card) => pile.cards.lastOption == Some(card))
}
