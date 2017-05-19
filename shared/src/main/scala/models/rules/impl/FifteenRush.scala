package models.rules.impl

import models.rules._

object FifteenRush extends GameRules(
  id = "fifteenrush",
  completed = true,
  title = "Fifteen Rush",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fifteen_rush.htm")),
  layout = "swf|t",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToFifteenOrAPair,
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.StockThenWaste,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
