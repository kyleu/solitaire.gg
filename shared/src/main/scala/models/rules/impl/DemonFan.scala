package models.rules.impl

import models.rules._

object DemonFan extends GameRules(
  id = "demonfan",
  completed = true,
  title = "Demon Fan",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/demon_fan.htm")),
  layout = ".::f|2t",
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(
    numPiles = 18,
    initialCards = InitialCards.RestOfDeck,
    suitMatchRuleForMovingStacks = SuitMatchRule.None,
    emptyFilledWith = FillEmptyWith.None
  )),
  special = Some(SpecialRules(redealsAllowed = 6))
)
