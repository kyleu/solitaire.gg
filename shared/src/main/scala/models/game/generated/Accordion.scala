package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object Accordion extends GameRules(
  id = "accordion",
  title = "Accordion",
  description = "This deck compression game was once known as \"Idle Year\" because it was believed that you could play for a year without winning, but players have now discovered strategies that make it possible to win almost every game.",
  cardRemovalMethod = CardRemovalMethod.StackSameRankOrSuitInWaste,
  waste = Some(
    WasteRules(
      name = "Waste",
      numPiles = 1,
      playableCards = WastePlayableCards.TopCardOnly
    )
  ),
  foundations = Seq(
    FoundationRules(
      name = "Foundation",
      numPiles = 4,
      lowRank = FoundationLowRank.DeckLowRank,
      initialCards = InitialCards.Count(0),
      suitMatchRule = SuitMatchRule.SameSuit,
      rankMatchRule = RankMatchRule.Up,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = false,
      maxCards = -1,
      canMoveFrom = FoundationCanMoveFrom.Never,
      mayMoveToFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau"),
      offscreen = true,
      autoMoveCards = true,
      autoMoveFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau")
    )
  ),
  complete = false
)
// scalastyle:on

