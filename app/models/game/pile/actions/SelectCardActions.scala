package models.game.pile.actions

import models.{CardRevealed, ResponseMessage, CardMoved}
import models.game.Rank._
import models.game.{Rank, GameState, Card}
import models.game.pile.{PileOption, Pile}

case class SelectCardAction(override val id: String, f: (Pile, Card, GameState) => Seq[ResponseMessage]) extends PileOption(id)

object SelectCardActions {
  val none = SelectCardAction("none", (pile, card, gameState) => Nil)

  val klondike = SelectCardAction("klondike", (pile, card, gameState) => {
    if(!card.u) {
      card.u = true
      Seq(CardRevealed(card))
    } else {
      val foundations = gameState.piles.filter(_.behavior == "foundation")
      val foundation = foundations.flatMap { f =>
        if(f.cards.isEmpty && card.r == Rank.Ace) {
          Some(f)
        } else if(f.cards.lastOption.map(_.s) == Some(card.s) && f.cards.lastOption.map(_.r) == Some(Rank.Ace) && card.r == Rank.Two) {
          Some(f)
        } else if(f.cards.lastOption.map(_.s) == Some(card.s) && f.cards.lastOption.map(_.r.value) == Some(card.r.value - 1)) {
          Some(f)
        } else {
          None
        }
      }.headOption
      foundation match {
        case Some(f) =>
          pile.removeCard(card)
          f.addCard(card)
          Seq(CardMoved(card.id, pile.id, f.id))
        case None => Nil
      }
    }
  })

  def drawToPile(cardsToDraw: Int, drawTo: String) = SelectCardAction("draw-to-waste", (pile, card, gameState) => {
    val waste = gameState.pilesById(drawTo)

    (0 to (cardsToDraw - 1)).flatMap { i =>
      val topCard = pile.cards.lastOption
      topCard match {
        case Some(tc) =>
          pile.removeCard(tc)
          val revealed = gameState.revealCardToAll(tc)

          waste.addCard(tc)
          tc.u = true
          revealed :+ CardMoved(tc.id, pile.id, drawTo, turnFaceUp = true)
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
    if(shouldMove) {
      Seq(CardMoved(card.id, pile.id, foundation.id))
    } else {
      Nil
    }
  })
}
