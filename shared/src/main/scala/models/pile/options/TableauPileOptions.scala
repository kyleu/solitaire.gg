package models.pile.options

import models.card.Rank
import models.pile.actions.{DragToActions, SelectCardActions}
import models.pile.constraints.Constraint
import models.rules._

object TableauPileOptions extends TableauPileOptionHelper {
  def apply(rules: TableauRules, deckOptions: DeckOptions, cardRemovalMethod: CardRemovalMethod) = {
    val emptyRanks = rules.emptyFilledWith match {
      case FillEmptyWith.Any => deckOptions.ranks
      case FillEmptyWith.None => Nil
      case FillEmptyWith.LowRank => Seq(deckOptions.lowRank)
      case FillEmptyWith.HighRank => Seq(deckOptions.highRank)
      case FillEmptyWith.HighRankUntilStockEmpty => Seq(deckOptions.highRank)
      case FillEmptyWith.HighRankOrLowRank => Seq(deckOptions.lowRank, deckOptions.highRank)
      case FillEmptyWith.Sevens => Seq(Rank.Seven)
    }

    val dragFromConstraint = dragFrom(
      rmr = rules.rankMatchRuleForMovingStacks,
      smr = rules.suitMatchRuleForMovingStacks,
      lowRank = deckOptions.lowRank,
      wrap = rules.wrap
    )

    val requireNonEmptyPiles = if (rules.emptyFilledWith == FillEmptyWith.HighRankUntilStockEmpty) { Seq("stock") } else { Seq.empty }
    val dragToConstraint = dragTo(
      crm = cardRemovalMethod,
      rules = rules,
      lowRank = deckOptions.lowRank,
      emptyPileRanks = emptyRanks,
      requireNonEmptyPiles = requireNonEmptyPiles
    )

    val (selectCardConstraint, selectCardAction) = {
      cardRemovalMethod match {
        case CardRemovalMethod.RemoveNinesOrPairsAddingToNineOr10JQK =>
          Some(Constraint.specificRanks(Seq(Rank.Nine))) -> Some(SelectCardActions.drawToPiles(() => 1, Seq("foundation-1")))
        case CardRemovalMethod.RemovePairsAddingToThirteenOrK =>
          Some(Constraint.specificRanks(Seq(Rank.King))) -> Some(SelectCardActions.drawToPiles(() => 1, Seq("foundation-1")))
        case CardRemovalMethod.BuildSequencesOnFoundation =>
          Some(Constraint.allOf("top-face-down", Constraint.topCardOnly, Constraint.faceDown)) -> Some(SelectCardActions.flip)
        case _ => None -> None
      }
    }

    PileOptions(
      direction = Some("d"),
      cardsShown = rules.cardsShown match {
        case 0 => None
        case x => Some(x)
      },
      dragFromConstraint = Some(dragFromConstraint),
      dragToConstraint = Some(dragToConstraint),
      dragToAction = cardRemovalMethod match {
        case CardRemovalMethod.BuildSequencesOnFoundation => None
        case _ => Some(DragToActions.remove())
      },
      selectCardConstraint = selectCardConstraint,
      selectCardAction = selectCardAction
    )
  }
}
