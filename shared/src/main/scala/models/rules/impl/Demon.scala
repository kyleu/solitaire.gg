// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Reserve initial cards (R0d): 40
 *   Number of reserve piles (R0n): 1
 *   Auto-fill an empty tableau from (T0af): 1
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Similar to (like): doublecanfield
 *   Low card (lowpip): -2 (?)
 *   Maximum deals from stock (maxdeals): 0
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Demon extends GameRules(
  id = "demon",
  title = "Demon",
  like = Some("doublecanfield"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/demon.htm")),
  description = "An two-deck version of ^canfield^, not quite as easy as ^doublecanfield^. \"Demon\" is the standard English name for Canfield. We " +
    "follow Thomas Warfield in fostering confusion by using the name for this different game.",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Rank.Unknown
  ),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      initialCards = 1,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 40,
      cardsFaceDown = -1
    )
  )
)
