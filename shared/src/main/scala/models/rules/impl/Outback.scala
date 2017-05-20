package models.rules.impl

import models.rules._

object Outback extends GameRules(
  id = "outback",
  completed = true,
  title = "Outback",
  related = Seq("adelaide"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/outback.htm")),
  layout = "swf|:.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(7),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
