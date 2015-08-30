// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Reserve initial cards (R0d): 13
 *   Number of reserve piles (R0n): 1
 *   Auto-fill an empty tableau from (T0af): 1
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 4
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Waste name (W0Nm): Gallery
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   *gallery (gallery): 1
 *   Similar to (like): canfield
 *   Low card (lowpip): -2 (?)
 *   Maximum deals from stock (maxdeals): 0
 */
object CanfieldGalleryMode extends GameRules(
  id = "canfieldgallery",
  completed = false,
  title = "Canfield (Gallery Mode)",
  like = Some("canfield"),
  description = "This is just ^canfield^ with a different user \"interface\": all the cards that would normally start in the stock are fanned out f" +
    "ace up, with the ones that would normally be playable if you were going through the stock three at a time automatically raised up " +
    "to indicate that they are playable.",
  layout = "swf|r|t",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
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
      initialCards = 1,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 13,
      cardsFaceDown = -1
    )
  )
)