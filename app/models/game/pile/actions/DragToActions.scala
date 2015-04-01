package models.game.pile.actions

import models.game.pile.Pile
import models.game.{ Card, GameState }
import models.{ CardRemoved, CardMoved, ResponseMessage }

case class DragToAction(id: String, f: (Pile, Seq[Card], Pile, GameState) => Seq[ResponseMessage])

object DragToActions {
  val moveCards = DragToAction("move-cards", (src, cards, tgt, gameState) => {
    cards.map { card =>
      src.removeCard(card)
      tgt.addCard(card)
      CardMoved(card.id, src.id, tgt.id)
    }
  })

  val remove = DragToAction("remove-cards", (src, cards, tgt, gameState) => {
    cards.flatMap { card =>
      src.removeCard(card)
      val targetCard = tgt.cards.last
      tgt.removeCard(targetCard)
      Seq(CardRemoved(card.id), CardRemoved(targetCard.id))
    }
  })
}
