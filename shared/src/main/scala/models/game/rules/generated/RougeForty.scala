// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object RougeForty extends GameRules(
  id = "rougeforty",
  title = "Rouge Forty",
  like = Some("rougeetnoir"),
  description = "A variation of ^rougeetnoir^ with a rectangular tableau. Invented by Thomas Warfield.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
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
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)

