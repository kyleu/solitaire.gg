// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object Manx extends GameRules(
  id = "manx",
  title = "Manx",
  description = "Build complete sequences by rearranging four piles regardless of suit in this game invented by Rick Holzgrafe of Solitaire Til Daw" +
  "n.",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      suitMatchRule = SuitMatchRule.Any,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      wrapFromKingToAce = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(

      pluralName = "Tail",
      numPiles = 1
    )
  ),
  complete = false
)

