package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Auto-fill an empty tableau from (T0af): -1 (Next tableau column)
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Deal cards from stock (dealchunk): 1 (One by one)
 *   Similar to (like): trigon
 *   Maximum deals from stock (maxdeals): 0
 */
object TrigonLeft extends GameRules(
  id = "trigonleft",
  completed = false,
  title = "Trigon Left",
  like = Some("trigon"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/trigon_left.htm")),
  description = "A blend of ^trigon^ and ^movingleft^.",
  layout = "swf|t",
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.NextPile,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
