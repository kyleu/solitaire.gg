// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Tableau cards face down (T0df): 100
 *   Tableau piles (T0n): 6
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Maximum deals from stock (maxdeals): 2 (2)
 *   Related games (related): passeul
 */
object BlindAlleys extends GameRules(
  id = "blindalleys",
  completed = false,
  title = "Blind Alleys",
  related = Seq("passeul"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/blind_alleys.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/blind_alleys.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/blind_alleys.htm")
  ),
  description = "A ^klondike^ variant with a square tableau, differing from ^passeul^ only in the number of passes through the deck allowed.",
  layout = "swf|t",
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
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
      numPiles = 6,
      initialCards = InitialCards.Count(3)
    )
  )
)