package models.rules.impl

import models.rules._

object Steve extends GameRules(
  id = "steve",
  completed = false,
  title = "Steve",
  like = Some("carlton"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/steve.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/steve.htm")
  ),
  layout = "sf|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
