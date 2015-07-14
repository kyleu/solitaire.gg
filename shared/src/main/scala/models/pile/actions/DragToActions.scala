package models.pile.actions

import models.card.Card
import models.game.GameState
import models.pile.Pile
import models.{ CardMoved, CardsMoved, ResponseMessage }

case class DragToAction(id: String, f: (Pile, Seq[Card], Pile, GameState) => Seq[ResponseMessage])

object DragToActions {
  val moveCards = DragToAction("move-cards", (src, cards, tgt, gameState) => {
    val cardIds = cards.map { card =>
      src.removeCard(card)
      tgt.addCard(card)
      card.id
    }
    val reveals = cards.filterNot(_.u).flatMap { c =>
      c.u = true
      gameState.revealCardToAll(c)
    }
    val moveMessage = CardsMoved(cardIds, src.id, tgt.id)
    if (src.cards.lastOption.exists(!_.u)) {
      val isReserve = src.pileSet.exists(_.behavior == "reserve")
      if (isReserve || gameState.players.exists(_.autoFlipOption)) {
        val last = src.cards.lastOption.getOrElse(throw new IllegalStateException())
        last.u = true
        reveals ++ (moveMessage +: gameState.revealCardToAll(last))
      } else {
        reveals :+ moveMessage
      }
    } else {
      reveals :+ moveMessage
    }
  })

  def remove() = DragToAction("remove-cards", (src, cards, tgt, gameState) => {
    val gy = gameState.pilesById("foundation-1")
    cards.flatMap { card =>
      src.removeCard(card)
      gy.addCard(card)

      val targetCard = tgt.cards.last
      tgt.removeCard(targetCard)
      gy.addCard(targetCard)

      Seq(CardMoved(card.id, src.id, "foundation-1"), CardMoved(targetCard.id, tgt.id, "foundation-1"))
    }
  })
}
