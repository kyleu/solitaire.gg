package models.game.pile.options

import models.game.{ GameState, Card, Rank }
import models.game.pile.Pile
import models.game.pile.constraints.Constraint
import models.game.rules._

object FoundationPileOptions {
  private[this] def getConstraints(lowRank: Rank, rules: FoundationRules) = {
    val dragFromConstraint = rules.canMoveFrom match {
      case FoundationCanMoveFrom.Always => Some(Constraint.topCardOnly)
      case FoundationCanMoveFrom.EmptyStock => Some(Constraint.pilesEmpty("stock"))
      case FoundationCanMoveFrom.Never => Some(Constraint.never)
    }

    val dragToConstraint = if(!rules.visible) {
      Some(Constraint.never)
    } else {
      Some(Constraint("foundation", (pile: Pile, cards: Seq[Card], gameState: GameState) => {
        if (cards.length == 1 && !rules.moveCompleteSequencesOnly) {
          if (rules.maxCards > 0 && pile.cards.length >= rules.maxCards) {
            false
          } else {
            val firstCard = cards.headOption.getOrElse(throw new IllegalStateException())
            if (pile.cards.isEmpty) {
              val siblings = gameState.pileSets.find(ps => ps.piles.exists(p => p.id == pile.id)).map(_.piles.filterNot(_.id == pile.id)).getOrElse {
                throw new IllegalStateException("Can't find pileset for [" + pile.id + "].")
              }
              val rankOk = if (lowRank == Rank.Unknown) {
                siblings.flatMap(_.cards.headOption).headOption match {
                  case Some(card) => firstCard.r == card.r
                  case None => true
                }
              } else {
                firstCard.r == lowRank
              }
              rules.initialCardRestriction match {
                case Some(FoundationInitialCardRestriction.SpecificColorUniqueSuits(c)) =>
                  val suits = siblings.flatMap(_.cards.headOption.map(_.s))
                  rankOk && firstCard.s.color == c && !suits.contains(firstCard.s)
                case Some(FoundationInitialCardRestriction.SpecificSuit(s)) =>
                  rankOk && firstCard.s == s
                case Some(FoundationInitialCardRestriction.UniqueColors) =>
                  val colors = siblings.flatMap(_.cards.headOption.map(_.s.color))
                  rankOk && !colors.contains(firstCard.s.color)
                case Some(FoundationInitialCardRestriction.UniqueSuits) =>
                  val suits = siblings.flatMap(_.cards.headOption.map(_.s))
                  rankOk && !suits.contains(firstCard.s)
                case None =>
                  rankOk
              }
            } else {
              val target = pile.cards.last
              val s = rules.suitMatchRule.check(target.s, firstCard.s)
              val r = rules.rankMatchRule.check(target.r, firstCard.r, lowRank, rules.wrapFromKingToAce)
              s && r
            }
          }
        } else if (rules.moveCompleteSequencesOnly) {
          cards.length == 13
        } else {
          false
        }
      }))
    }
    (dragFromConstraint, dragToConstraint)
  }

  def apply(rules: FoundationRules, deckOptions: DeckOptions) = {
    if (rules.lowRank == FoundationLowRank.Ascending) {
      (1 to rules.numPiles).map { i =>
        val (dragFromConstraint, dragToConstraint) = getConstraints(Rank.allByValue(i), rules)
        PileOptions(
          cardsShown = Some(rules.cardsShown),
          dragFromConstraint = dragFromConstraint,
          dragToConstraint = dragToConstraint
        )
      }
    } else {
      val lowRank = rules.lowRank match {
        case FoundationLowRank.AnyCard => Rank.Unknown
        case FoundationLowRank.Ascending => throw new IllegalStateException()
        case FoundationLowRank.DeckHighRank => deckOptions.highRank
        case FoundationLowRank.DeckLowRank => deckOptions.lowRank
        case FoundationLowRank.SpecificRank(r) => r
      }

      val (dragFromConstraint, dragToConstraint) = getConstraints(lowRank, rules)
      Seq(PileOptions(
        cardsShown = Some(rules.cardsShown),
        dragFromConstraint = dragFromConstraint,
        dragToConstraint = dragToConstraint
      ))
    }
  }
}
