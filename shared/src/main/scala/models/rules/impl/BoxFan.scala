package models.rules.impl

import models.rules._

object BoxFan extends GameRules(
  id = "boxfan",
  completed = true,
  title = "Box Fan",
  like = Some("fan"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/box_fan.htm")),
  layout = "::::::f|t",
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      name = "Fan",
      numPiles = 16,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
