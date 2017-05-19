package models.rules.impl

import models.rules._

object FortyAndEight extends GameRules(
  id = "fortyandeight",
  completed = true,
  title = "Forty and Eight",
  related = Seq("lower48"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/forty_and_eight.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/forty_and_eight.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/forty_and_eight.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/forty-and-eight.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/FortyandEight.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/forty_and_eight.htm")
  ),
  layout = ":::sw|f|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
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
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
