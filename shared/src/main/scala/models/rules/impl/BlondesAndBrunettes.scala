package models.rules.impl

import models.card.Rank
import models.pile.set.PileSet
import models.rules._

object BlondesAndBrunettes extends GameRules(
  id = "blondesandbrunettes",
  completed = true,
  title = "Blondes and Brunettes",
  like = Some("signora"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/blondes_and_brunettes.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/blondes_and_brunettes.html")
  ),
  layout = "swf|r:t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Unknown),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, initialCards = 1, suitMatchRule = SuitMatchRule.AlternatingColors)),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.StockThenWaste,
      mayMoveToNonEmptyFrom = PileSet.Behavior.allButReserve,
      mayMoveToEmptyFrom = PileSet.Behavior.allButReserve
    )
  ),
  reserves = Some(ReserveRules(initialCards = 10))
)
