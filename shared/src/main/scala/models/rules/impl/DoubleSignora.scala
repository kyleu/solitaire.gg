package models.rules.impl

import models.card.Rank
import models.pile.set.PileSet
import models.rules._

object DoubleSignora extends GameRules(
  id = "doublesignora",
  completed = true,
  title = "Double Signora",
  like = Some("signora"),
  related = Seq("empressofitaly"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_signora.htm")),
  layout = "swf|r:::t",
  deckOptions = DeckOptions(numDecks = 4, lowRank = Rank.Unknown),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 16, suitMatchRule = SuitMatchRule.AlternatingColors)),
  tableaus = Seq(
    TableauRules(
      numPiles = 11,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
      mayMoveToNonEmptyFrom = PileSet.Behavior.allButReserve,
      mayMoveToEmptyFrom = PileSet.Behavior.allButReserve
    )
  ),
  reserves = Some(ReserveRules(initialCards = 21))
)
