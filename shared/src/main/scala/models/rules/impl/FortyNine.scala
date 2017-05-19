package models.rules.impl

import models.rules._

object FortyNine extends GameRules(
  id = "fortynine",
  completed = true,
  title = "Forty-Nine",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/forty_nine.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/forty_nine.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/forty-nine.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/FortyNine.htm")
  ),
  layout = "swf|::t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
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
      initialCards = InitialCards.Count(7),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
