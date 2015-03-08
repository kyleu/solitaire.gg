package models.game.pile

import models.{ResponseMessage, CardMoved}
import models.game.{GameState, Card}

class Stock(override val id: String) extends Pile(id, "stock") {
  override def canSelectCard(card: Card) = Some(card) == this.cards.lastOption
  override def canSelectPile = this.cards.isEmpty

  override def onSelectCard(card: Card, gameState: GameState) = {
    val waste = gameState.pilesById("waste")

    (0 to 2).flatMap { i =>
      val topCard = cards.lastOption
      topCard match {
        case Some(tc) =>
          if(i == 0) {
            if(tc != card) {
              log.warn("SelectCard for game [" + id + "]: Selected card [" + card + "] is not stock top card [" + topCard + "].")
            }
          }
          removeCard(tc)
          val revealed = gameState.revealCardToAll(tc)

          waste.addCard(tc)
          tc.u = true
          log.info("Stock card [" + tc + "] moved to waste.")
          revealed :+ CardMoved(tc.id, "stock", "waste", turnFaceUp = true)
        case None => Nil
      }
    }
  }

  override def onSelectPile(gameState: GameState) = {
    val waste = gameState.pilesById("waste")

    waste.cards.reverse.map { card =>
      waste.removeCard(card)
      addCard(card)
      CardMoved(card.id, "waste", "stock", turnFaceDown = true)
    }
  }
}
