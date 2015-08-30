// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 2
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau name (T0Nm): Fan
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Maximum cards per tableau (T0m): 3 (3 cards)
 *   Tableau piles (T0n): 18
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): freefan
 */
object LuckyFan extends GameRules(
  id = "luckyfan",
  completed = false,
  title = "Lucky Fan",
  like = Some("freefan"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lucky_fan.htm")),
  description = "A version of ^freefan^ in which no fan may hold more than three cards.",
  layout = "f|c|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Fan",
      numPiles = 18,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank,
      maxCards = 3
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 2
    )
  )
)