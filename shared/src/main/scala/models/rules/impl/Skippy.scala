package models.rules.impl

import models.card.Rank
import models.rules._

object Skippy extends GameRules(
  id = "skippy",
  completed = true,
  title = "Skippy",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/skippy.htm")),
  layout = "::.swf|::::::c|t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Unknown),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, initialCards = 1, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(CellRules())
)
