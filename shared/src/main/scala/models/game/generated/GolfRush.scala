package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object GolfRush extends GameRules(
  id = "golfrush",
  title = "Golf Rush",
  description = "A variant of ^golf^ played on with a ^klondike^-style tableau.",
  victoryCondition = VictoryCondition.AllOnFoundationOrStock,
  stock = Some(
    StockRules(
      name = "Stock",
      dealTo = StockDealTo.Foundation,
      maximumDeals = Some(1),
      cardsDealt = StockCardsDealt.Count(1),
      stopAfterPartialDeal = true,
      createPocketWhenEmpty = false,
      galleryMode = false
    )
  ),
  foundations = Seq(
    FoundationRules(
      name = "Foundation",
      numPiles = 1,
      lowRank = FoundationLowRank.AnyCard,
      initialCards = InitialCards.Count(1),
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpOrDown,
      wrapFromKingToAce = false,
      moveCompleteSequencesOnly = false,
      maxCards = 0,
      canMoveFrom = FoundationCanMoveFrom.Never,
      mayMoveToFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau"),
      offscreen = false,
      autoMoveCards = true,
      autoMoveFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau")
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Tableau",
      numPiles = 7,
      initialCards = InitialCards.PileIndex,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      wrapFromKingToAce = false,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Nowhere,
      emptyFilledWith = TableauFillEmptyWith.None,
      mayMoveToNonEmptyFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau"),
      mayMoveToEmptyFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau"),
      maxCards = 0,
      actionDuringDeal = PileAction.None,
      actionAfterDeal = PileAction.None,
      pilesWithLowCardsAtBottom = 0
    )
  ),
  complete = false
)
// scalastyle:on

