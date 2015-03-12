package models.game.pile

import models.CardMoved
import models.game.{GameState, Card}

import scala.collection.mutable.ArrayBuffer

class Stock(
  id: String,
  cardsToDraw: Int = 3,
  cardsShown: Option[Int] = Some(1),
  direction: Option[String] = None,
  options: Map[String, String] = Map.empty
) extends Pile(id, "stock", ArrayBuffer.empty, cardsShown, direction, options) {

  private val drawTo = options.getOrElse("draw-to", "waste")
  private val redrawTo = options.get("redraw-to")

  override def canSelectCard(card: Card) = Some(card) == this.cards.lastOption
  override def canSelectPile = this.cards.isEmpty

  override def onSelectCard(card: Card, gameState: GameState) = {
    val waste = gameState.pilesById(drawTo)

    (0 to (cardsToDraw - 1)).flatMap { i =>
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
          log.debug("Stock card [" + tc + "] moved from [" + id + "] to [" + drawTo + "].")
          revealed :+ CardMoved(tc.id, id, drawTo, turnFaceUp = true)
        case None => Nil
      }
    }
  }

  override def onSelectPile(gameState: GameState) = redrawTo match {
    case Some(pileId) =>
      val target = gameState.pilesById(pileId)
      target.cards.reverse.map { card =>
        target.removeCard(card)
        addCard(card)
        CardMoved(card.id, pileId, id, turnFaceDown = true)
      }
    case None => Nil
  }
}
