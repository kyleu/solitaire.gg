package models.rules.impl

import models.rules._

object Castile extends GameRules(
  id = "castile",
  completed = true,
  title = "Castile",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/castile.htm")),
  layout = "::f|::r|t",
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  reserves = Some(ReserveRules(numPiles = 4, initialCards = 7))
)
