package models.rules.impl

import models.rules._

object FortressOfMercy extends GameRules(
  id = "fortressofmercy",
  completed = false,
  title = "Fortress of Mercy",
  like = Some("fortress"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fortress_of_mercy.htm")),
  layout = "f|t",
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      initialCards = 2,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  special = Some(
    SpecialRules(
      drawsAllowed = 1
    )
  )
)
