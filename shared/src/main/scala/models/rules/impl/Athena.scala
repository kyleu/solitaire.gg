// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau cards face down (T0df): 102
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Similar to (like): klondike
 *   Maximum deals from stock (maxdeals): 0
 *   Related games (related): bureau, minerva
 */
object Athena extends GameRules(
  id = "athena",
  title = "Athena",
  like = Some("klondike"),
  related = Seq("bureau", "minerva"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/athena.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Athena.html.en"),
    Link("Elton Gahr on HobbyHub", "www.solitairelaboratory.com/buildingranks.html")
  ),
  description = "A ^klondike^ variation with a rectangular starting tableau in which cards alternate face-up and face-down.",
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
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
