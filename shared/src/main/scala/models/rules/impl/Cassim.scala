package models.rules.impl

import models.rules._

object Cassim extends GameRules(
  id = "cassim",
  completed = true,
  title = "Cassim",
  like = Some("alibaba"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/cassim.htm")),
  layout = "swf|t",
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
