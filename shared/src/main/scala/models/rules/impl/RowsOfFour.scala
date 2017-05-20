package models.rules.impl

import models.rules._

object RowsOfFour extends GameRules(
  id = "rowsoffour",
  completed = true,
  title = "Rows of Four",
  like = Some("diplomat"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/rows_of_four.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/rows_of_four.htm")
  ),
  layout = "swf|.:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToEmptyFrom = Seq("stock", "pyramid", "waste", "pocket", "reserve", "cell", "foundation")
    )
  )
)
