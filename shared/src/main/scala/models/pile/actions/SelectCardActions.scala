package models.pile.actions

import models.card.Card
import models.game.GameState
import models.pile.Pile
import models.pile.set.PileSet
import models.{CardsMoved, ResponseMessage}

case class SelectCardAction(id: String, f: (Pile, Card, GameState) => Seq[ResponseMessage])

object SelectCardActions {
  val none = SelectCardAction("none", (_, _, _) => Nil)

  def drawToPile(cardsToDraw: Int, drawTo: String, turn: Option[Boolean] = None) = drawToPiles(() => cardsToDraw, Seq(drawTo), turn)

  def drawToPiles(cardsToDraw: () => Int, drawTo: Seq[String], turn: Option[Boolean] = None) = {
    SelectCardAction("draw-to-piles", (pile, _, gameState) => {
      drawTo.flatMap { p =>
        val tgt = gameState.pilesById(p)
        val cards = pile.cards.takeRight(cardsToDraw()).reverse
        cards.flatMap { card =>
          moveCard(card, pile, tgt, gameState, turn = turn)
        }
      }
    })
  }

  def drawToNonEmptyPiles(cardsToDraw: () => Int, drawTo: Seq[String], turn: Option[Boolean] = None) = {
    SelectCardAction("draw-to-piles", (pile, _, gameState) => {
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
  }

  def drawToEmptyPiles(behavior: PileSet.Behavior) = SelectCardAction("draw-to-empty", (pile, _, gameState) => {
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
    val msg = CardsMoved(Seq(card.id), src.id, tgt.id, turn = turn)
    if ((turn.isEmpty || turn.exists(x => x)) && !card.u) {
      card.u = true
      gameState.revealCardToAll(card) :+ msg
    } else {
      Seq(msg)
    }
  }

  val flip = SelectCardAction("flip", (_, card, gameState) => {
    if (!card.u) {
      card.u = true
      gameState.revealCardToAll(card)
    } else {
      Nil
    }
  })
}
