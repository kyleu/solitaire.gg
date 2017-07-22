package models.rules.impl

import models.rules._

object Stewart extends GameRules(
  id = "stewart",
  completed = true,
  title = "Stewart",
  like = Some("martha"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/stewart.htm")),
  layout = ":f|2t",
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
