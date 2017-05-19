package models.rules.impl

import models.rules._

object FreeFan extends GameRules(
  id = "freefan",
  completed = true,
  title = "FreeFan",
  like = Some("fan"),
  related = Seq("luckyfan"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/free_fan.htm")),
  layout = ".:::::f:c|t",
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
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
  ),
  cells = Some(CellRules(numPiles = 2))
)
