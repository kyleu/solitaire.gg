// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object OddAndEven extends GameRules(
  id = "oddandeven",
  title = "Odd and Even",
  description = "A difficult, old and remarkably stupid game where foundation piles are built up by twos and no building is allowed on the tableau.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      rankMatchRule = RankMatchRule.UpBy2,
      wrapFromKingToAce = true
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      rankMatchRule = RankMatchRule.UpBy2,
      wrapFromKingToAce = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock
    )
  ),
  complete = false
)

