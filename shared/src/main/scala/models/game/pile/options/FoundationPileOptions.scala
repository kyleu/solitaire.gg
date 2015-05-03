package models.game.pile.options

import models.game.{ GameState, Card, Rank }
import models.game.pile.Pile
import models.game.pile.constraints.{ Constraint, Constraints }
import models.game.rules.{ FoundationLowRank, DeckOptions, FoundationCanMoveFrom, FoundationRules }

object FoundationPileOptions {
  def apply(rules: FoundationRules, deckOptions: DeckOptions) = {
    val lowRank = rules.lowRank match {
      case FoundationLowRank.AnyCard => Rank.Unknown
      case FoundationLowRank.Ascending => throw new NotImplementedError()
      case FoundationLowRank.DeckHighRank => deckOptions.highRank
      case FoundationLowRank.DeckLowRank => deckOptions.lowRank
      case FoundationLowRank.SpecificRank(r) => r
    }

    val dragFromConstraint = rules.canMoveFrom match {
      case FoundationCanMoveFrom.Always => Some(Constraints.topCardOnly)
      case FoundationCanMoveFrom.EmptyStock => Some(Constraints.pilesEmpty("stock"))
      case FoundationCanMoveFrom.Never => Some(Constraints.never)
    }

    val dragToConstraint = Some(Constraint("foundation", (pile: Pile, cards: Seq[Card], gameState: GameState) => {
      if (cards.length == 1) {
        if(rules.maxCards > -1 && pile.cards.length >= rules.maxCards) {
          false
        } else {
          val firstCard = cards.headOption.getOrElse(throw new IllegalStateException())
          if (pile.cards.isEmpty) {
            if (lowRank == Rank.Unknown) {
              true
            } else {
              firstCard.r == lowRank
            }
          } else {
            val target = pile.cards.last
            val s = rules.suitMatchRule.check(target.s, firstCard.s)
            val r = rules.rankMatchRule.check(target.r, firstCard.r, lowRank)
            s && r
          }
        }
      } else {
        false
      }
    }))

    PileOptions(
      cardsShown = Some(rules.cardsShown),
      dragFromConstraint = dragFromConstraint,
      dragToConstraint = dragToConstraint
    )
  }
}
