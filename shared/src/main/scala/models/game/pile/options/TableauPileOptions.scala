package models.game.pile.options

import models.game.{GameState, Card, Rank}
import models.game.pile.Pile
import models.game.pile.actions.SelectCardActions
import models.game.pile.constraints.Constraint
import models.game.rules._

object TableauPileOptions {
  def apply(rules: TableauRules, deckOptions: DeckOptions) = {
    val emptyRanks = rules.emptyFilledWith match {
      case FillEmptyWith.Any => Seq(Rank.Unknown)
      case FillEmptyWith.None => Nil
      case FillEmptyWith.Aces => Seq(Rank.Ace)
      case FillEmptyWith.Kings => Seq(Rank.King)
      case FillEmptyWith.KingsUntilStockEmpty => throw new NotImplementedError()
      case FillEmptyWith.KingsOrAces => Seq(Rank.King, Rank.Ace)
      case FillEmptyWith.Sevens => Seq(Rank.Seven)
    }

    val dragFromConstraint = Constraint("sequence",
      dragFrom(rules.rankMatchRuleForMovingStacks, rules.suitMatchRuleForMovingStacks, deckOptions.lowRank)
    )

    val dragToConstraint = Constraint("tableau",
      dragTo(rules.rankMatchRuleForBuilding, rules.suitMatchRuleForBuilding, emptyRanks)
    )

    PileOptions(
      direction = Some("d"),
      cardsShown = rules.cardsShown match {
        case 0 => None
        case x => Some(x)
      },
      dragFromConstraint = Some(dragFromConstraint),
      dragToConstraint = Some(dragToConstraint),
      selectCardConstraint = Some(Constraint.allOf("top-face-down", Constraint.topCardOnly, Constraint.faceDown)),
      selectCardAction = Some(SelectCardActions.flip)
    )
  }

  private[this] def dragFrom(rmr: RankMatchRule, smr: SuitMatchRule, lowRank: Rank) = (pile: Pile, cards: Seq[Card], gameState: GameState) => {
    if (cards.exists(!_.u)) {
      false
    } else {
      var valid = true
      var lastCard: Option[Card] = None

      for (c <- cards) {
        lastCard.foreach { lc =>
          if (!rmr.check(lc.r, c.r, lowRank)) {
            valid = false
          } else if (!smr.check(lc.s, c.s)) {
            valid = false
          }
        }
        lastCard = Some(c)
      }
      valid
    }
  }

  private[this] def dragTo(rrm: RankMatchRule, smr: SuitMatchRule, emptyPileRanks: Seq[Rank]) = { (pile: Pile, cards: Seq[Card], gameState: GameState) =>
    if (pile.cards.isEmpty) {
      emptyPileRanks.length match {
        case 0 => cards.length == 1
        case _ => cards.exists(c => emptyPileRanks.contains(c.r))
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
  }
}
