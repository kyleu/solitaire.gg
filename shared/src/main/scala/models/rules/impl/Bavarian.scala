package models.rules.impl

import models.rules._

object Bavarian extends GameRules(
  id = "bavarian",
  completed = true,
  title = "Bavarian",
  like = Some("german"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/bavarian_patience.htm")),
  layout = "::::sw|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Up,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
