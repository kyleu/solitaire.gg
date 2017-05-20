package models.rules.impl

import models.card.Rank
import models.pile.set.PileSet
import models.rules._

object Balcony extends GameRules(
  id = "balcony",
  completed = true,
  title = "Balcony",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/balcony.htm")),
  layout = ".swf|rt",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 4, initialCards = 1, suitMatchRule = SuitMatchRule.AlternatingColors, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.StockThenWaste,
      mayMoveToNonEmptyFrom = PileSet.Behavior.allButReserve
    )
  ),
  reserves = Some(ReserveRules(initialCards = 7))
)
