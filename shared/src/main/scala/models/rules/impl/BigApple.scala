package models.rules.impl

import models.card.Rank
import models.pile.set.PileSet
import models.rules._

object BigApple extends GameRules(
  id = "bigapple",
  completed = false,
  title = "Big Apple",
  like = Some("gotham"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/big_apple.htm")),
  layout = "sf|c|t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Unknown),
  stock = Some(StockRules(dealTo = StockDealTo.Manually, maximumDeals = Some(1))),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      mayMoveToEmptyFrom = Seq(PileSet.Behavior.Stock, PileSet.Behavior.Cell)
    )
  ),
  cells = Some(CellRules(numPiles = 3))
)
