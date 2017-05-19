package models.rules.impl

import models.rules._

object ExiledKings extends GameRules(
  id = "exiledkings",
  completed = true,
  title = "Exiled Kings",
  like = Some("citadel"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/exiled_kings.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/exiled-kings.htm")
  ),
  layout = "::f|t",
  foundations = Seq(FoundationRules(numPiles = 4, initialCards = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank,
      actionDuringDeal = PileAction.MoveToFoundation
    )
  )
)
