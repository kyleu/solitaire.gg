package models.game.pile.options

import models.game.Rank
import models.game.pile.actions.SelectCardActions
import models.game.pile.constraints.{ Constraint, KlondikeConstraintLogic, Constraints }
import models.game.rules.{ DeckOptions, FillEmptyWith, TableauRules }

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

    val dragFromConstraint = Constraint(
      "sequence",
      KlondikeConstraintLogic.dragFrom(rules.rankMatchRuleForMovingStacks, rules.suitMatchRuleForMovingStacks, deckOptions.lowRank)
    )

    PileOptions(
      direction = Some("d"),
      cardsShown = rules.cardsShown match {
        case 0 => None
        case x => Some(x)
      },
      dragFromConstraint = Some(dragFromConstraint),
      dragToConstraint = Some(Constraints.klondikeTableauDragTo(emptyRanks)),
      selectCardConstraint = Some(Constraints.allOf("top-face-down", Constraints.topCardOnly, Constraints.faceDown)),
      selectCardAction = Some(SelectCardActions.flip)
    )
  }
}
