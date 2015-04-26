package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object CircleEight extends GameRules(
  id = "circleeight",
  title = "Circle Eight",
  description = "Move all cards to the tableau to win this game, but you can't move a card once it is on the tableau.",
  victoryCondition = VictoryCondition.NoneInStock,
  stock = Some(
    StockRules(
      name = "Stock",
      dealTo = StockDealTo.Waste,
      maximumDeals = Some(2),
      cardsDealt = StockCardsDealt.Count(1),
      stopAfterPartialDeal = true,
      createPocketWhenEmpty = false,
      galleryMode = false
    )
  ),
  waste = Some(
    WasteRules(
      name = "Waste",
      numPiles = 1,
      playableCards = WastePlayableCards.TopCardOnly
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Tableau",
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Up,
      wrapFromKingToAce = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Nowhere,
      emptyFilledWith = TableauFillEmptyWith.Aces,
      mayMoveToNonEmptyFrom = Seq("Waste"),
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

