package models.rules.impl

import models.rules._

object NapoleonsSquare extends GameRules(
  id = "napoleonssquare",
  completed = false,
  title = "Napoleon's Square",
  like = Some("blockade"),
  related = Seq("napoleonsshoulder"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Napoleon's_Square"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/napoleons_square.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/napoleon_s_square.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/napoleon-square.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/napoleons_square.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/NapoleonsSquare.htm")
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
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
