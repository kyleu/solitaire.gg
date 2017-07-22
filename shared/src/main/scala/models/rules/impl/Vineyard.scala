package models.rules.impl

import models.rules._

object Vineyard extends GameRules(
  id = "vineyard",
  completed = true,
  title = "Vineyard",
  related = Seq("tenbyone", "winery"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/vineyard.htm")),
  layout = ":::f|t",
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
