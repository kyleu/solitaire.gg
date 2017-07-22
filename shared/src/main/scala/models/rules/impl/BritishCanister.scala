package models.rules.impl

import models.rules._

object BritishCanister extends GameRules(
  id = "britishcanister",
  completed = true,
  title = "British Canister",
  like = Some("canister"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/british_canister.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/canister.htm")
  ),
  layout = "::f|t",
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
