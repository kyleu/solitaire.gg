package models.rules.impl

import models.card.Rank
import models.pile.set.PileSet
import models.rules._

object NewYork extends GameRules(
  id = "newyork",
  completed = false,
  title = "New York",
  related = Seq("gotham"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/new_york.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/new_york.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/new_york.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/new-york.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/NewYork.htm")
  ),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Unknown),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Manually,
      maximumDeals = Some(1)
    )
  ),
  waste = Some(
    WasteRules(
      numPiles = 3
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToEmptyFrom = Seq(PileSet.Behavior.Stock, PileSet.Behavior.Waste)
    )
  )
)
