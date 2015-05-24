// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Waste name (W0Nm): Gallery
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   *gallery (gallery): 1
 *   Similar to (like): klondike
 *   Maximum deals from stock (maxdeals): 0
 */
object KlondikeGalleryMode extends GameRules(
  id = "klondikegallery",
  title = "Klondike (Gallery Mode)",
  like = Some("klondike"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Klondike_(solitaire)"),
    Link("Robert Abbott's Strategy Guide", "www.logicmazes.com/sol/")
  ),
  description = "The world's most famous solitaire game played in gallery mode so all stock cards are always visible and the playable ones are rais" +
    "ed",
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(
    WasteRules(
      name = "Gallery"
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
