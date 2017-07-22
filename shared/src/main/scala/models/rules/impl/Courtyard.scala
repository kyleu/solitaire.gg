package models.rules.impl

import models.rules._

object Courtyard extends GameRules(
  id = "courtyard",
  completed = false,
  title = "Courtyard",
  like = Some("busyaces"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/courtyard.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/courtyard.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/courtyard.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/courtyard.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Courtyard.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/courtyard.html")
  ),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock
    )
  )
)
