package models.rules.impl

import models.card.Rank
import models.pile.set.PileSet
import models.rules._

object Gotham extends GameRules(
  id = "gotham",
  completed = false,
  title = "Gotham",
  like = Some("newyork"),
  related = Seq("bigapple"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/gotham.htm")),
  layout = "::sw|f|t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Unknown),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Manually,
      maximumDeals = Some(1)
    )
  ),
  waste = Some(
    WasteRules(
      numPiles = 3
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      mayMoveToEmptyFrom = Seq(PileSet.Behavior.Stock, PileSet.Behavior.Waste)
    )
  )
)
