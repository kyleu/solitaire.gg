// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object RougeEtNoir extends GameRules(
  id = "rougeetnoir",
  title = "Rouge et Noir",
  description = "A variant of ^diavolo^ with a different tableau and no waste. Invented by Charles Jewell.",
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
      moveCompleteSequencesOnly = true,
      canMoveFrom = FoundationCanMoveFrom.Never
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDDDDDU",
        "DDDDDDU",
        "DDDDDU",
        "DDDDU",
        "DDDU",
        "DDU",
        "DU",
        "U",
        "U"
      ),
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)

