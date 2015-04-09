package models.game.pile.constraints

import models.game.Rank
import models.game.Rank._

object RankConstraintLogic {
  def specificRank(r: Rank) = Constraint("rank-" + r, (pile, cards, gameState) => {
    cards.tail.isEmpty && cards.headOption.getOrElse(throw new IllegalStateException()).r == r
  })

  val sameRank = Constraint("same-rank", (pile, cards, gameState) => {
    val topCardRank = pile.cards.lastOption.map(_.r).getOrElse(Rank.Unknown)
    val firstDraggedCardRank = cards.headOption.getOrElse(throw new IllegalStateException()).r
    topCardRank == firstDraggedCardRank
  })

  val lowerRank = Constraint("lower-rank", (pile, cards, gameState) => {
    val firstDraggedCardRank = cards.headOption.getOrElse(throw new IllegalStateException()).r
    pile.cards.lastOption match {
      case Some(c) => c.r match {
        case Two => firstDraggedCardRank == Ace
        case _ => c.r.value == firstDraggedCardRank.value + 1
      }
      case None => false
    }
  })

  val alternatingRank = Constraint("alternating-rank", (pile, cards, gameState) => {
    val topCardRank = pile.cards.last.r
    val firstDraggedCardRank = cards.headOption.getOrElse(throw new IllegalStateException()).r
    topCardRank match {
      case King => firstDraggedCardRank == Queen
      case Ace => firstDraggedCardRank == Two
      case Two => firstDraggedCardRank == Ace || firstDraggedCardRank == Three
      case _ => topCardRank.value == firstDraggedCardRank.value + 1 || topCardRank.value == firstDraggedCardRank.value - 1
    }
  })

  val alternatingRankToFoundation = Constraint("alternating-rank", (pile, cards, gameState) => {
    if (cards.tail.nonEmpty || pile.cards.lastOption != cards.headOption) {
      false
    } else {
      val foundation = gameState.pilesById("foundation")
      val topCardRank = foundation.cards.last.r
      val firstCard = cards.headOption.getOrElse(throw new IllegalStateException())
      topCardRank match {
        case King => firstCard.r == Queen
        case Ace => firstCard.r == Two
        case Two => firstCard.r == Ace || firstCard.r == Three
        case _ => topCardRank.value == firstCard.r.value + 1 || topCardRank.value == firstCard.r.value - 1
      }
    }
  })
}
