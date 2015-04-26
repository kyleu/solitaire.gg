// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object BeehiveGalleryMode extends GameRules(
  id = "beehivegallery",
  title = "Beehive (Gallery Mode)",
  description = "This is just ^beehive^ with a different user \"interface\": all the cards that would normally start in the stock are fanned out fa" +
  "ce up, with the ones that would normally be playable if you were going through the stock three at a time automatically raised up t" +
  "o indicate that they are playable.",
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(
    WasteRules(
      name = "Gallery"
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 13,
      lowRank = FoundationLowRank.Ascending,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Equal,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true,
      maxCards = 4,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Equal,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Equal,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 10,
      cardsFaceDown = 100
    )
  ),
  complete = false
)

