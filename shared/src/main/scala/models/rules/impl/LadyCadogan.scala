package models.rules.impl

import models.rules._

object LadyCadogan extends GameRules(
  id = "ladycadogan",
  completed = true,
  title = "Lady Cadogan",
  related = Seq("fortydevils"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lady_cadogan.htm")),
  layout = "swf:f|:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      name = "Left Foundation",
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits)
    ),
    FoundationRules(
      name = "Right Foundation",
      setNumber = 1,
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
