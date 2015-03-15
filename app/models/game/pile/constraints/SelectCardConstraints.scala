package models.game.pile.constraints

import models.game.Rank._
import models.game.{GameState, Rank, Card}
import models.game.pile.{PileOption, Pile}

case class SelectCardConstraint(override val id: String, f: (Pile, Card, GameState) => Boolean) extends PileOption(id)

object SelectCardConstraints {
  val never = SelectCardConstraint("never", (pile, card, gameState) => false)

  val topCardOnly = SelectCardConstraint("top-card-only", (pile, card, gameState) => pile.cards.lastOption == Some(card))

  val klondike = SelectCardConstraint("klondike", (pile, card, gameState) => {
    if(!topCardOnly.f(pile, card, gameState)) {
      false
    } else if(!card.u) {
      true
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
        case Some(f) => true
        case None => false
      }
    }
  })

  val alternatingRankToFoundation = SelectCardConstraint("alternating-rank", (pile, card, gameState) => {
    if(!topCardOnly.f(pile, card, gameState)) {
      false
    } else {
      val foundation = gameState.pilesById("foundation")
      val topCardRank = foundation.cards.last.r
      topCardRank match {
        case King => card.r == Queen
        case Ace => card.r == Two
        case Two => card.r == Ace || card.r == Three
        case _ => topCardRank.value == card.r.value + 1 || topCardRank.value == card.r.value - 1
      }
    }
  })
}
