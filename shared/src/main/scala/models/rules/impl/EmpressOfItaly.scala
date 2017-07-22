package models.rules.impl

import models.card.Rank
import models.pile.set.PileSet
import models.rules._

object EmpressOfItaly extends GameRules(
  id = "empressofitaly",
  completed = false,
  title = "Empress of Italy",
  like = Some("doublesignora"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/empress_of_italy.htm")),
  layout = "swf|r|t",
  deckOptions = DeckOptions(numDecks = 4, lowRank = Rank.Unknown),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 16, initialCards = 1, suitMatchRule = SuitMatchRule.AlternatingColors)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 11,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.StockThenWaste,
      mayMoveToNonEmptyFrom = PileSet.Behavior.allButReserve,
      mayMoveToEmptyFrom = PileSet.Behavior.allButReserve
    )
  ),
  reserves = Some(ReserveRules(initialCards = 19))
)
