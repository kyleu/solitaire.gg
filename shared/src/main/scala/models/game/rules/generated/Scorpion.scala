// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Scorpion extends GameRules(
  id = "scorpion",
  title = "Scorpion",
  related = Seq("chelicera", "chinese"),
  description = "A game with a seven-by-seven tableau, where three cards in the first four piles start face down. Unsorted stacks of cards can be m" +
  "oved around, as in ^yukon^, but cards cannot be moved to the foundation until they form complete sequences, as in ^spider^.",
  stock = Some(
    StockRules(
      name = "Reserve",
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDUUUU",
        "DDDUUUU",
        "DDDUUUU",
        "DDDUUUU",
        "UUUUUUU",
        "UUUUUUU",
        "UUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)

