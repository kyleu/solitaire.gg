package models.rules.impl

import models.rules._

object Parliament extends GameRules(
  id = "parliament",
  completed = false,
  title = "Parliament",
  like = Some("congress"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/parliament.htm")),
  layout = "swf|t",
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
      initialCards = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToEmptyFrom = Seq("stock", "pyramid", "waste", "pocket", "reserve", "cell", "foundation")
    )
  )
)
