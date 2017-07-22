package models.rules.impl

import models.card.Rank
import models.pile.set.PileSet
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
  foundations = IndexedSeq(FoundationRules(numPiles = 8, initialCards = 1, suitMatchRule = SuitMatchRule.AlternatingColors, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToNonEmptyFrom = PileSet.Behavior.allButReserve,
      mayMoveToEmptyFrom = Seq(PileSet.Behavior.Waste)
    )
  ),
  reserves = Some(ReserveRules(initialCards = 10))
)
