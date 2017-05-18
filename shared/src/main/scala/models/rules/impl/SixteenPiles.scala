package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): -1
 *   Deal order (RDd): 9 (Columns, left to right, top to bottom)
 *   Allowed pick ups/redeals (RDn): 2 (2)
 *   Shuffle before redealing (RDs): 1 (Yes)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau name (T0Nm): Fan
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 16
 *   Tableau rank match rule for building (T0r): 64 (Build equal)
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 */
object SixteenPiles extends GameRules(
  id = "sixteenpiles",
  completed = true,
  title = "Sixteen Piles",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/sixteen_piles.htm")),
  layout = "::::::f|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Fan",
      numPiles = 16,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Equal,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2
    )
  )
)
