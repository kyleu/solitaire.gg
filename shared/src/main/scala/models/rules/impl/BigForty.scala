package models.rules.impl

import models.rules._

object BigForty extends GameRules(
  id = "bigforty",
  completed = true,
  title = "Big Forty",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/big_forty.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/BigForty.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/big-forty.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/big_forty.htm")
  ),
  layout = "swf|t",
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
