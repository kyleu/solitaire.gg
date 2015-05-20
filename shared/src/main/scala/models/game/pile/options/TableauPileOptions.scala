package models.game.pile.options

import models.game.pile.actions.{ DragToActions, SelectCardActions }
import models.game.pile.constraints.Constraint
import models.game.rules._
import models.game.Rank

object TableauPileOptions extends TableauPileOptionHelper {
  def apply(rules: TableauRules, deckOptions: DeckOptions, cardRemovalMethod: CardRemovalMethod) = {
    val emptyRanks = rules.emptyFilledWith match {
      case FillEmptyWith.Any => deckOptions.ranks
      case FillEmptyWith.None => Nil
      case FillEmptyWith.Aces => Seq(Rank.Ace)
      case FillEmptyWith.Kings => Seq(Rank.King)
      case FillEmptyWith.KingsUntilStockEmpty => throw new NotImplementedError() // TODO
      case FillEmptyWith.KingsOrAces => Seq(Rank.King, Rank.Ace)
      case FillEmptyWith.Sevens => Seq(Rank.Seven)
    }

    val dragFromConstraint = dragFrom(rules.rankMatchRuleForMovingStacks, rules.suitMatchRuleForMovingStacks, deckOptions.lowRank, rules.wrapFromKingToAce)
    val dragToConstraint = {
      dragTo(
        crm = cardRemovalMethod,
        rmr = rules.rankMatchRuleForBuilding,
        smr = rules.suitMatchRuleForBuilding,
        lowRank = deckOptions.lowRank,
        emptyPileRanks = emptyRanks,
        maxCards = rules.maxCards,
        wrapFromKingToAce = rules.wrapFromKingToAce
      )
    }

    val (selectCardConstraint, selectCardAction) = {
      cardRemovalMethod match {
        case CardRemovalMethod.RemoveNinesOrPairsAddingToNineOr10JQK =>
          Some(Constraint.specificRanks(Seq(Rank.Nine))) -> Some(SelectCardActions.drawToPiles(1, Seq("foundation-1")))
        case CardRemovalMethod.RemovePairsAddingToThirteenOrK =>
          Some(Constraint.specificRanks(Seq(Rank.King))) -> Some(SelectCardActions.drawToPiles(1, Seq("foundation-1")))
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
