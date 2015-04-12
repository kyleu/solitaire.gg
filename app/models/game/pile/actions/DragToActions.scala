package models.game.pile.actions

import models.game.pile.Pile
import models.game.{ Card, GameState }
import models.{ CardMoved, ResponseMessage }

case class DragToAction(id: String, f: (Pile, Seq[Card], Pile, GameState) => Seq[ResponseMessage])

object DragToActions {
  val moveCards = DragToAction("move-cards", (src, cards, tgt, gameState) => {
    cards.map { card =>
      src.removeCard(card)
      val tgtIdx = tgt.addCard(card)
      CardMoved(card.id, src.id, tgt.id, tgtIdx)
    }
  })

  def remove(graveyard: String = "graveyard") = DragToAction("remove-cards", (src, cards, tgt, gameState) => {
    val gy = gameState.pilesById(graveyard)
    cards.flatMap { card =>
      src.removeCard(card)
      gy.addCard(card)

      val targetCard = tgt.cards.last
      tgt.removeCard(targetCard)
      val tgtIdx = gy.addCard(targetCard)

      Seq(CardMoved(card.id, src.id, graveyard, tgtIdx), CardMoved(targetCard.id, tgt.id, graveyard, tgtIdx))
    }
  })
}
