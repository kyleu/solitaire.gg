// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game._
import models.game.rules._

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
 *   Deal cards from stock (dealchunk): -1 (Fewer in each pass)
 *   Similar to (like): canfield
 *   Low card (lowpip): -2 (?)
 *   Maximum deals from stock (maxdeals): 3 (3)
 */
object CanfieldRush extends GameRules(
  id = "canfieldrush",
  completed = true,
  title = "Canfield Rush",
  like = Some("canfield"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/canfield_rush.htm")),
  description = "A ^canfield^ variant where cards are dealt by threes in the first pass, by twos in the second and one-at-a-time in the last. Easie" +
    "r than standard Canfield, but still a challenging game.",
  layout = Some("swf|:r:t"),
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
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
