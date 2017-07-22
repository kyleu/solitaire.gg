package models.rules.impl

import models.rules._

object Limited extends GameRules(
  id = "limited",
  completed = false,
  title = "Limited",
  like = Some("fortythieves"),
  related = Seq("doublelimited"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/limited.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Forty_Thieves_(card_game)"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/limited.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/limited.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Limited.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/limited.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/limited.htm"),
    Link("Battleline Games", "www.limitedsolitaire.com/LimitedSolitaireInstructions.html"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Limited.html")
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
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
