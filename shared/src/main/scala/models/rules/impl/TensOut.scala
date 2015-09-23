package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Keep foundation off-screen (F0i): 1 (Yes)
 *   Number of foundation piles (F0n): 1 (1 stack)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Left mouse interface function (leftfunc): 1
 *   Similar to (like): fourteenout
 *   Card removal method (pairs): 4 (Remove pairs adding to 10, 10-10, J-J, Q-Q, or K-K)
 */
object TensOut extends GameRules(
  id = "tensout",
  completed = true,
  title = "Tens Out",
  like = Some("fourteenout"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/tens_out.htm")),
  description = "A variation of ^fourteenout^ where we remove pairs adding to 10.",
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
