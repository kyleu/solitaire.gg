// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object LadyCadogan extends GameRules(
  id = "ladycadogan",
  title = "Lady Cadogan",
  description = "Thomas Warfield's ^rougeetnoir^ variant where we build regardless of suit instead of in alternating colors.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      name = "Left Foundation",
      numPiles = 4,
      wrapFromKingToAce = true
    ),
    FoundationRules(
      name = "Right Foundation",
      setNumber = 1,
      numPiles = 4,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)

