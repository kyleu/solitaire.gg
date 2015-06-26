// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Tableau cards face down (T0df): 0
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 6
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 1 (One by one)
 *   Similar to (like): thoughtful
 *   Maximum deals from stock (maxdeals): 1 (1)
 */
object AuntMary extends GameRules(
  id = "auntmary",
  title = "Aunt Mary",
  like = Some("thoughtful"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/aunt_mary.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/aunt_mary.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/aunt-mary.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Aunt_Mary.html.en"),
    Link("L.Schaffer on Hobby Hub", "www.hobbyhub360.com/index.php/how-to-play-aunt-mary-solitaire-14352/")
  ),
  description = "A difficult ^klondike^ variation where the tableau contains one fewer pile but all cards are face up.",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
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
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
