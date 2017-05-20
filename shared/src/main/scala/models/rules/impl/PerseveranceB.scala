package models.rules.impl

import models.rules._

object PerseveranceB extends GameRules(
  id = "perseveranceb",
  completed = true,
  title = "Perseverance B",
  like = Some("perseverancea"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/perseverance.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/perseverance.htm")
  ),
  layout = "::::f|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.None,
      actionDuringDeal = PileAction.MoveKingsToBottom
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2
    )
  )
)
