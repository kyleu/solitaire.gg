// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Tableau cards face down (T0df): 100
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Related games (related): tripleeasthaven, doubleeasthaven
 */
object Easthaven extends GameRules(
  id = "easthaven",
  completed = true,
  title = "Easthaven",
  related = Seq("tripleeasthaven", "doubleeasthaven"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/easthaven.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Easthaven.html"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/easthaven.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Easthaven.html.en"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/easthaven.htm")
  ),
  description = "A one-deck cross between ^spider^ and ^klondike^.",
  layout = "s.f|t",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(3)
    )
  )
)