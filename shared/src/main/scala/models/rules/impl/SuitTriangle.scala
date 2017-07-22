package models.rules.impl

import models.rules._

object SuitTriangle extends GameRules(
  id = "suittriangle",
  completed = true,
  title = "Suit Triangle",
  like = Some("doubleklondike"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/suit_triangle.htm")),
  layout = "swf|.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
