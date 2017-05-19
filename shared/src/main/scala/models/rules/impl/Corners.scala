package models.rules.impl

import models.card.Rank
import models.rules._

object Corners extends GameRules(
  id = "corners",
  completed = false,
  title = "Corners",
  like = Some("czarina"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/corners.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/corners.htm")
  ),
  layout = "swf|t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  stock = Some(StockRules(maximumDeals = Some(3))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 4, initialCards = 1)),
  tableaus = Seq(
    TableauRules(
      numPiles = 5,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  )
)
