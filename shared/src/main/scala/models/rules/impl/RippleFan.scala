package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): 0 (None)
 *   Deal order (RDd): 9 (Columns, left to right, top to bottom)
 *   Allowed pick ups/redeals (RDn): -1 (Unlimited)
 *   Pickup order (RDp): 9 (Columns, left to right, top to bottom)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 13
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): cruel
 *   Right mouse interface function (rightfunc): 0
 */
object RippleFan extends GameRules(
  id = "ripplefan",
  completed = true,
  title = "Ripple Fan",
  like = Some("cruel"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/ripple_fan.htm")),
  layout = ".::::f|t",
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
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
