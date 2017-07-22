package models.rules.impl

import models.rules._

object Saxony extends GameRules(
  id = "saxony",
  completed = true,
  title = "Saxony",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/saxony.htm")),
  layout = "sf|.r|c:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.Reserve, maximumDeals = Some(1))),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, initialCards = 8)),
  tableaus = IndexedSeq(
    TableauRules(
      name = "Reserve",
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules(initialCards = 4)),
  reserves = Some(ReserveRules(name = "Tableau", numPiles = 8, cardsFaceDown = -1))
)
