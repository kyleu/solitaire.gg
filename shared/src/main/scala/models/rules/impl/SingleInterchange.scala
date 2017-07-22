package models.rules.impl

import models.rules._

object SingleInterchange extends GameRules(
  id = "singleinterchange",
  completed = true,
  title = "Single Interchange",
  like = Some("unlimited"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/single_interchange.htm")),
  layout = "swf|:t",
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 5,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.EvenNumbered,
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
