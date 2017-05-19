package models.rules.impl

import models.card.Rank
import models.rules._

object Wood extends GameRules(
  id = "wood",
  completed = false,
  title = "Wood",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/wood.htm")),
  layout = "swf|r|t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Unknown),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, initialCards = 1, suitMatchRule = SuitMatchRule.AlternatingColors, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToNonEmptyFrom = Seq("stock", "pyramid", "waste", "pocket", "cell", "foundation", "tableau"),
      mayMoveToEmptyFrom = Seq("waste")
    )
  ),
  reserves = Some(ReserveRules(initialCards = 10))
)
