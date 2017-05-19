package models.rules.impl

import models.card.Rank
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
  foundations = Seq(FoundationRules(numPiles = 8)),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
      mayMoveToNonEmptyFrom = Seq("stock", "pyramid", "waste", "pocket", "cell", "foundation", "tableau"),
      mayMoveToEmptyFrom = Seq("stock", "pyramid", "waste", "pocket", "cell", "foundation", "tableau")
    )
  ),
  reserves = Some(ReserveRules(initialCards = 11))
)
