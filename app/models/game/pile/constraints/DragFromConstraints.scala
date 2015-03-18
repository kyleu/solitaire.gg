package models.game.pile.constraints

import models.game.{GameState, Rank, Card}
import models.game.pile.Pile

case class DragFromConstraint(id: String, f: (Pile, Seq[Card], GameState) => Boolean, clientOptions: Option[Map[String, String]] = None)

object DragFromConstraints {
  val never = DragFromConstraint("never", (pile, cards, gameState) => false)

  val topCardOnly = DragFromConstraint("top-card-only", (pile, cards, gameState) => cards.headOption == pile.cards.lastOption)

  val klondike = DragFromConstraint("klondike", (pile, cards, gameState) => if(cards.exists(!_.u)) {
    false
  } else {
    var valid = true
    var lastCard: Option[Card] = None

    for(c <- cards) {
      if(lastCard.isDefined) {
        if(c.s.color == lastCard.get.s.color) {
          valid = false
        }
        if(c.r == Rank.Ace || c.r.value != (lastCard.get.r.value - 1)) {
          valid = false
        }
      }
      lastCard = Some(c)
    }
    valid
  })

  def pilesEmpty(piles: String*) = DragFromConstraint("piles-empty", (pile, cards, gameState) => {
    var ret = true
    for(p <- piles) {
      if(gameState.pilesById(p).cards.nonEmpty) {
        ret = false
      }
    }
    ret
  }, Some(Map("piles" -> piles.mkString(","))))
}
