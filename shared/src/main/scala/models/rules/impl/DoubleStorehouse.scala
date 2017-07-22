package models.rules.impl

import models.card.Rank
import models.rules._

object DoubleStorehouse extends GameRules(
  id = "doublestorehouse",
  completed = true,
  title = "Double Storehouse",
  like = Some("storehouse"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_storehouse.htm")),
  layout = "swf|r::.t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Two),
  stock = Some(StockRules(maximumDeals = Some(3))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, initialCards = 8, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(ReserveRules(name = "Storehouse", initialCards = 19, cardsFaceDown = -1))
)
