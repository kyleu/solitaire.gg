package models.rules.impl

import models.rules._

object QuadrupleTrigon extends GameRules(
  id = "quadrupletrigon",
  completed = true,
  title = "Quadruple Trigon",
  like = Some("trigon"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/quadruple_trigon.htm")),
  layout = "swf|:.t",
  deckOptions = DeckOptions(numDecks = 4),
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 16,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 16,
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
