package models.rules.impl

import models.card.Rank
import models.pile.set.PileSet
import models.rules._

object Roman extends GameRules(
  id = "roman",
  completed = false,
  title = "Roman",
  like = Some("signora"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/roman_patience.htm")),
  layout = "swf|r|t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Unknown),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 8)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
      mayMoveToNonEmptyFrom = PileSet.Behavior.allButReserve,
      mayMoveToEmptyFrom = PileSet.Behavior.allButReserve
    )
  ),
  reserves = Some(ReserveRules(initialCards = 11))
)
