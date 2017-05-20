package models.rules.impl

import models.rules._

object LuckyFan extends GameRules(
  id = "luckyfan",
  completed = false,
  title = "Lucky Fan",
  like = Some("freefan"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lucky_fan.htm")),
  layout = "f|c|t",
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      name = "Fan",
      numPiles = 18,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank,
      maxCards = 3
    )
  ),
  cells = Some(CellRules(numPiles = 2))
)
