package models.rules.impl

import models.rules._

object HalfCell extends GameRules(
  id = "halfcell",
  completed = false,
  title = "HalfCell",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/halfcell.htm")),
  layout = "f|c|t",
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 2,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueColors),
      suitMatchRule = SuitMatchRule.AlternatingColors,
      maxCards = 26,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules())
)
