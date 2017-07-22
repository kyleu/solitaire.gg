package models.rules.impl

import models.rules._

object Midshipman extends GameRules(
  id = "midshipman",
  completed = true,
  title = "Midshipman",
  like = Some("maria"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/midshipman.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/midshipman.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/midshipman.htm"),
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/midshipman.html"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/midshipman.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Midshipman.htm")
  ),
  layout = "swf|:t",
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
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(2),
      suitMatchRuleForBuilding = SuitMatchRule.DifferentSuits,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
