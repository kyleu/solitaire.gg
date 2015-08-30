// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-fill an empty tableau from (T0af): 10 (First stock then waste)
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Left mouse interface function (leftfunc): 1
 *   Maximum deals from stock (maxdeals): 0
 *   Card removal method (pairs): 13 (Remove pairs adding to 15 or A-A)
 */
object FifteenRush extends GameRules(
  id = "fifteenrush",
  completed = false,
  title = "Fifteen Rush",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fifteen_rush.htm")),
  description = "The layout is like ^klondike^, but you remove pairs that add to fifteen or pairs of aces.",
  layout = Some("swf|t"),
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToFifteenOrAPair,
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.StockThenWaste,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)