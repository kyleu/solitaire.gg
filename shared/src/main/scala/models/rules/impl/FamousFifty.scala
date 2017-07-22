package models.rules.impl

import models.rules._

object FamousFifty extends GameRules(
  id = "famousfifty",
  completed = true,
  title = "Famous Fifty",
  like = Some("fortythieves"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/famous_fifty.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/famous-fifty.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/FamousFifty.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/famous_fifty.htm")
  ),
  layout = "swf|.t",
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
      numPiles = 10,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
