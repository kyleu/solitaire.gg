package models.game.pile.constraints

import models.game.pile.Pile
import models.game.rules.{ SuitMatchRule, RankMatchRule }
import models.game.{ GameState, Rank, Card }

object KlondikeConstraintLogic {
  def dragFrom(rankMatchRule: RankMatchRule, suitMatchRule: SuitMatchRule, lowRank: Rank) = (pile: Pile, cards: Seq[Card], gameState: GameState) => {
    if (cards.exists(!_.u)) {
      false
    } else {
      var valid = true
      var lastCard: Option[Card] = None

      for (c <- cards) {
        lastCard.foreach { lc =>
          if (!rankMatchRule.check(lc.r, c.r, lowRank)) {
            valid = false
          } else if(!suitMatchRule.check(lc.s, c.s)) {
            valid = false
          }
        }
        lastCard = Some(c)
      }
      valid
    }
  }

  def tableauDragTo(emptyPileRanks: Seq[Rank]) = (pile: Pile, cards: Seq[Card], gameState: GameState) => if (pile.cards.isEmpty) {
    emptyPileRanks.length match {
      case 0 => cards.length == 1
      case _ => cards.exists( c => emptyPileRanks.contains(c.r))
    }
  } else {
    val topCard = pile.cards.lastOption.getOrElse(throw new IllegalStateException())
    val firstDraggedCard = cards.headOption.getOrElse(throw new IllegalStateException())
    if (!topCard.u) {
      false
    } else {
      if (topCard.s.color == firstDraggedCard.s.color) {
        false
      } else if (topCard.r == Rank.Ace || firstDraggedCard.r == Rank.King) {
        false
      } else {
        topCard.r.value == firstDraggedCard.r.value + 1
      }
    }
  }

  val selectCard = (pile: Pile, cards: Seq[Card], gameState: GameState) => {
    val firstCard = cards.headOption.getOrElse(throw new IllegalStateException())
    if (pile.cards.lastOption != cards.headOption) {
      false
    } else if (!firstCard.u) {
      true
    } else {
      false
    }
  }
}
