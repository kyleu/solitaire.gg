// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 0
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Similar to (like): klondike
 *   Maximum deals from stock (maxdeals): 0
 */
object Saratoga extends GameRules(
  id = "saratoga",
  completed = false,
  title = "Saratoga",
  like = Some("klondike"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/saratoga.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/saratoga.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Saratoga.html.en"),
    Link("Elton Gahr on HobbyHub", "www.hobbyhub360.com/index.php/solitaire-how-to-play-saratoga-13682/")
  ),
  description = "This is just ^klondike^ with the cards dealt face up.",
  layout = "swf|t",
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
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)