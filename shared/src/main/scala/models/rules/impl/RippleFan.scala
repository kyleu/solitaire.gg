package models.rules.impl

import models.rules._

object RippleFan extends GameRules(
  id = "ripplefan",
  completed = true,
  title = "Ripple Fan",
  like = Some("cruel"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/ripple_fan.htm")),
  layout = ".::::f|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
