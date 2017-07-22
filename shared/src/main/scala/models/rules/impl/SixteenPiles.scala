package models.rules.impl

import models.rules._

object SixteenPiles extends GameRules(
  id = "sixteenpiles",
  completed = true,
  title = "Sixteen Piles",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/sixteen_piles.htm")),
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
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Equal,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2
    )
  )
)
