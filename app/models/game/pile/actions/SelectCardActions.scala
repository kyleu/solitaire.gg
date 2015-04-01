package models.game.pile.actions

import models.{ CardRemoved, CardRevealed, ResponseMessage, CardMoved }
import models.game.Rank._
import models.game.{ Rank, GameState, Card }
import models.game.pile.Pile

case class SelectCardAction(id: String, f: (Pile, Card, GameState) => Seq[ResponseMessage])

object SelectCardActions {
  val none = SelectCardAction("none", (pile, card, gameState) => Nil)

  val klondike = SelectCardAction("klondike", (pile, card, gameState) => {
    if (!card.u) {
      card.u = true
      Seq(CardRevealed(card))
    } else {
      val foundations = gameState.piles.filter(_.behavior == "foundation")
      val foundation = foundations.flatMap { f =>
        if (f.cards.isEmpty && card.r == Rank.Ace) {
          Some(f)
        } else if (f.cards.lastOption.map(_.s) == Some(card.s) && f.cards.lastOption.map(_.r) == Some(Rank.Ace) && card.r == Rank.Two) {
          Some(f)
        } else if (f.cards.lastOption.map(_.s) == Some(card.s) && f.cards.lastOption.map(_.r.value) == Some(card.r.value - 1)) {
          Some(f)
        } else {
          None
        }
      }.headOption
      foundation match {
        case Some(f) => moveCard(card, pile, f, gameState)
        case None => Nil
      }
    }
  })

  def drawToPile(cardsToDraw: Int, drawTo: String, turnFaceUp: Boolean) = drawToPiles(cardsToDraw, Seq(drawTo), turnFaceUp)

  def drawToPiles(cardsToDraw: Int, drawTo: Seq[String], turnFaceUp: Boolean) = SelectCardAction("draw-to-piles", (pile, card, gameState) => drawTo.flatMap { p =>
    val tgt = gameState.pilesById(p)
    (0 to (cardsToDraw - 1)).flatMap { i =>
      val topCard = pile.cards.lastOption
      topCard match {
        case Some(tc) => moveCard(tc, pile, tgt, gameState, turnFaceUp = turnFaceUp)
        case None => Nil
      }
    }
  })

  val alternatingRank = SelectCardAction("alternating-rank", (pile, card, gameState) => {
    val foundation = gameState.pilesById("foundation")
    val topCardRank = foundation.cards.last.r
    val selectedCardRank = card.r
    val shouldMove = topCardRank match {
      case King => selectedCardRank == Queen
      case Ace => selectedCardRank == Two
      case Two => selectedCardRank == Ace || selectedCardRank == Three
      case _ => topCardRank.value == selectedCardRank.value + 1 || topCardRank.value == selectedCardRank.value - 1
    }
    if (shouldMove) {
      moveCard(card, pile, foundation, gameState)
    } else {
      Nil
    }
  })

  def drawToEmptyPiles(behavior: String) = SelectCardAction("draw-to-empty", (pile, card, gameState) => {
    val piles = gameState.piles.filter(_.behavior == behavior)
    piles.flatMap { p =>
      if (p.cards.isEmpty) {
        pile.cards.lastOption match {
          case Some(tc) => moveCard(tc, pile, p, gameState, turnFaceUp = true)
          case None => Nil
        }
      } else {
        Nil
      }
    }
  })

  val remove = SelectCardAction("remove-card", (pile, card, gameState) => {
    pile.removeCard(card)
    Seq(CardRemoved(card.id))
  })

  private def moveCard(card: Card, src: Pile, tgt: Pile, gameState: GameState, turnFaceUp: Boolean = true) = {
    src.removeCard(card)
    tgt.addCard(card)
    val msg = CardMoved(card.id, src.id, tgt.id, turnFaceUp = turnFaceUp)
    if (turnFaceUp && !card.u) {
      card.u = true
      val revealed = gameState.revealCardToAll(card)
      revealed ++ Seq(msg)
    } else {
      Seq(msg)
    }
  }
}
