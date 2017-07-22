package models.rules.impl

import models.rules._

object OldCarlton extends GameRules(
  id = "oldcarlton",
  completed = true,
  title = "Old Carlton",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/old_carlton.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/old_carlton.php")
  ),
  layout = "sf|.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
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
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any
    )
  )
)
