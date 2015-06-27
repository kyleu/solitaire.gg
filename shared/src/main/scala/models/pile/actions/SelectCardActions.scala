package models.pile.actions

import models.{ ResponseMessage, CardMoved }
import models.game.{ GameState, Card }
import models.pile.Pile

case class SelectCardAction(id: String, f: (Pile, Card, GameState) => Seq[ResponseMessage])

object SelectCardActions {
  val none = SelectCardAction("none", (pile, card, gameState) => Nil)

  def drawToPile(cardsToDraw: Int, drawTo: String, turn: Option[Boolean] = None) = drawToPiles(() => cardsToDraw, Seq(drawTo), turn)

  def drawToPiles(cardsToDraw: () => Int, drawTo: Seq[String], turn: Option[Boolean] = None) = {
    SelectCardAction("draw-to-piles", (pile, card, gameState) => {
      drawTo.flatMap { p =>
        val tgt = gameState.pilesById(p)
        val cards = pile.cards.takeRight(cardsToDraw()).reverse
        cards.flatMap { card =>
          moveCard(card, pile, tgt, gameState, turn = turn)
        }
      }
    })
  }

  def drawToNonEmptyPiles(cardsToDraw: () => Int, drawTo: Seq[String], turn: Option[Boolean] = None) = SelectCardAction("draw-to-piles", (pile, card, gameState) => {
    drawTo.flatMap { p =>
      val tgt = gameState.pilesById(p)
      if (tgt.cards.nonEmpty) {
        val cards = pile.cards.takeRight(cardsToDraw()).reverse
        cards.flatMap { card =>
          moveCard(card, pile, tgt, gameState, turn = turn)
        }
      } else {
        Nil
      }
    }
  })

  def drawToEmptyPiles(behavior: String) = SelectCardAction("draw-to-empty", (pile, card, gameState) => {
    val piles = gameState.pileSets.filter(_.behavior == behavior).flatMap(_.piles)
    piles.flatMap { p =>
      if (p.cards.isEmpty) {
        pile.cards.lastOption match {
          case Some(tc) => moveCard(tc, pile, p, gameState, turn = Some(true))
          case None => Nil
        }
      } else {
        Nil
      }
    }
  })

  def moveCard(card: Card, src: Pile, tgt: Pile, gameState: GameState, turn: Option[Boolean] = None) = {
    src.removeCard(card)
    tgt.addCard(card)
    val msg = CardMoved(card.id, src.id, tgt.id, turn = turn)
    if (turn.exists(x => x) && !card.u) {
      card.u = true
      gameState.revealCardToAll(card) :+ msg
    } else {
      Seq(msg)
    }
  }

  val flip = SelectCardAction("flip", (pile, card, gameState) => {
    if (!card.u) {
      card.u = true
      gameState.revealCardToAll(card)
    } else {
      Nil
    }
  })
}
