package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): -1 (Fewer in each pass)
 *   Maximum deals from stock (maxdeals): 3 (3)
 *   Related games (related): doublegoldrush
 */
object GoldRush extends GameRules(
  id = "goldrush",
  completed = true,
  title = "Gold Rush",
  related = Seq("doublegoldrush"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/gold_rush.htm")),
  description = "A ^klondike^ variation where the number of cards dealt to the waste decreases with each pass through the stock.",
  layout = "swf|t",
  stock = Some(
    StockRules(
      maximumDeals = Some(3),
      cardsDealt = StockCardsDealt.FewerEachTime
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
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
