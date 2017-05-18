package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Card initially dealt into cells (C0d): 3 (3 cards)
 *   Number of cells (C0n): 3
 *   Deal order (RDd): 0 (Rows, left to right, bottom to top)
 *   *RDdo (RDdo): 20
 *   Allowed pick ups/redeals (RDn): 2 (2)
 *   Pickup order (RDp): 1 (Columns, left to right, bottom to top)
 *   *RDpo (RDpo): 20
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 7 (7 cards)
 *   Tableau piles (T0n): 7
 *   Tableau rank match rule for building (T0r): 160 (Build up or down)
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 */
object SevenBySeven extends GameRules(
  id = "sevenbyseven",
  completed = false,
  title = "Seven by Seven",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/seven_by_seven.htm")),
  layout = "f|c|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(7),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 3,
      initialCards = 3
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2
    )
  )
)
