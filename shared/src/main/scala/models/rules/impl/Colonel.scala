package models.rules.impl

import models.card.Rank
import models.pile.set.PileSet
import models.rules._

object Colonel extends GameRules(
  id = "colonel",
  completed = false,
  title = "Colonel",
  related = Seq("signora"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/colonel.htm")),
  layout = "swf|r|t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Unknown),
  stock = Some(StockRules(maximumDeals = Some(2))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 8)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
      mayMoveToNonEmptyFrom = PileSet.Behavior.allButReserve,
      mayMoveToEmptyFrom = PileSet.Behavior.allButReserve
    )
  ),
  reserves = Some(ReserveRules(initialCards = 11))
)
