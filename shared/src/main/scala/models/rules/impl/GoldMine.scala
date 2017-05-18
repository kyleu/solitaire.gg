package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 0 (None)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 0 (Any card)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Similar to (like): klondike
 *   Maximum deals from stock (maxdeals): 1 (1)
 */
object GoldMine extends GameRules(
  id = "goldmine",
  completed = true,
  title = "Gold Mine",
  like = Some("klondike"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/gold_mine.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Gold_Mine.html.en")
  ),
  layout = "swf|t",
  stock = Some(
    StockRules(
      maximumDeals = Some(1),
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
      initialCards = InitialCards.Count(0)
    )
  )
)
