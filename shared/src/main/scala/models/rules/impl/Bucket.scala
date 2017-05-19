package models.rules.impl

import models.rules._

object Bucket extends GameRules(
  id = "bucket",
  completed = false,
  title = "Bucket",
  like = Some("canister"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/bucket.htm")),
  layout = "f|t",
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
