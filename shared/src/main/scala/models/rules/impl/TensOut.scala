package models.rules.impl

import models.rules._

object TensOut extends GameRules(
  id = "tensout",
  completed = true,
  title = "Tens Out",
  like = Some("fourteenout"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/tens_out.htm")),
  layout = "f|t",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToTenOr10PairOrJPairOrQPairOrKPair,
  foundations = Seq(
    FoundationRules(
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
