package models.game.pile.options

import models.game.pile.actions.SelectCardActions
import models.game.pile.constraints.Constraint
import models.game.rules._
import models.game.{Card, Rank}

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

    val dragFromConstraint = dragFrom(rules.rankMatchRuleForMovingStacks, rules.suitMatchRuleForMovingStacks, deckOptions.lowRank)
    val dragToConstraint = dragTo(rules.rankMatchRuleForBuilding, rules.suitMatchRuleForBuilding, deckOptions.lowRank, emptyRanks, rules.maxCards)

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

  private[this] def dragFrom(rmr: RankMatchRule, smr: SuitMatchRule, lowRank: Rank) = {
    Constraint("sequence", (pile, cards, gameState) => {
      if (cards.exists(!_.u)) {
        false
      } else {
        var valid = true
        var lastCard: Option[Card] = None
        for(c <- cards) {
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
    })
  }

  private[this] def dragTo(rmr: RankMatchRule, smr: SuitMatchRule, lowRank: Rank, emptyPileRanks: Seq[Rank], maxCards: Int) = {
    Constraint("tableau", (pile, cards, gameState) => {
      if(maxCards > 0 && pile.cards.length + cards.length > maxCards) {
        false
      } else {
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
            if (smr.check(topCard.s, firstDraggedCard.s)) {
              rmr.check(topCard.r, firstDraggedCard.r, lowRank)
            } else {
              false
            }
          }
        }
      }
    })
  }
}
