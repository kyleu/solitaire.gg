package models.rules.impl

import models.rules._

object Arizona extends GameRules(
  id = "arizona",
  completed = true,
  title = "Arizona",
  related = Seq("phoenix"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/arizona.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/arizona.htm")
  ),
  layout = "wf|t",
  waste = Some(WasteRules(name = "Reserve")),
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any
    )
  )
)
