package models.rules.impl

import models.rules._

object Kingdom extends GameRules(
  id = "kingdom",
  completed = false,
  title = "Kingdom",
  links = Seq(
    Link("Solsuite Solitaire", "www.solsuite.com/games/kingdom.htm"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/kingdom.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/kingdom.php")
  ),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
      suitMatchRule = SuitMatchRule.Any,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
