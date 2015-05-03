// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Diavolo extends GameRules(
  id = "diavolo",
  title = "Diavolo",
  related = Seq("rougeetnoir"),
  description = "A ^klondike^ variant with four foundation piles that are built one card at a time, while the other four need completed sequences.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 2,
      suitMatchRule = SuitMatchRule.SameColor,
      wrapFromKingToAce = true
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 2,
      suitMatchRule = SuitMatchRule.SameColor,
      wrapFromKingToAce = true
    ),
    FoundationRules(
      setNumber = 2,
      numPiles = 4,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)

