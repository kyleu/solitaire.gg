package models.rules.impl

import models.rules._

object LuckyPiles extends GameRules(
  id = "luckypiles",
  completed = false,
  title = "Lucky Piles",
  like = Some("luckythirteen"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lucky_piles.htm")),
  layout = "f|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
