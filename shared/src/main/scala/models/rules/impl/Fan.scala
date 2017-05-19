package models.rules.impl

import models.rules._

object Fan extends GameRules(
  id = "fan",
  completed = true,
  title = "Fan",
  related = Seq("boxfan", "freefan", "ceilingfan", "midnightclover"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fan.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/fan.htm")
  ),
  layout = ".::f|2t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Fan",
      numPiles = 18,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
