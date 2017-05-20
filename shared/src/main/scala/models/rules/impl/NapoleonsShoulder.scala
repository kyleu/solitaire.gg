package models.rules.impl

import models.rules._

object NapoleonsShoulder extends GameRules(
  id = "napoleonsshoulder",
  completed = true,
  title = "Napoleon's Shoulder",
  like = Some("napoleonssquare"),
  links = Seq(Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/napoleons_shoulder.html")),
  layout = ".swf|t",
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
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToEmptyFrom = Seq("waste")
    )
  )
)
