package models.rules.impl

import models.rules._

object Manx extends GameRules(
  id = "manx",
  completed = true,
  title = "Manx",
  related = Seq("tabbycat"),
  links = Seq(
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Manx.html"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/manx.html")
  ),
  layout = "sf|ct",
  stock = Some(StockRules(dealTo = StockDealTo.Tableau, maximumDeals = Some(1))),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, suitMatchRule = SuitMatchRule.Any, moveCompleteSequencesOnly = true, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any
    )
  ),
  cells = Some(CellRules(pluralName = "Tail", numPiles = 1))
)
