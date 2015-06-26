// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): -1
 *   Reserve initial cards (R0d): 13
 *   Number of reserve piles (R0n): 1
 *   Auto-fill an empty tableau from (T0af): 1
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 5
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Similar to (like): doublecanfield
 *   Low card (lowpip): 1 (A)
 *   Maximum deals from stock (maxdeals): 3 (3)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object VariegatedCanfield extends GameRules(
  id = "variegatedcanfield",
  title = "Variegated Canfield",
  like = Some("doublecanfield"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/variegated_canfield.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/variegated_canfield.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/variegated_canfield.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/variegated-canfield.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/VariegatedCanfield.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/canfield/variegated_canfield.htm")
  ),
  description = "A difficult two-deck version of ^canfield^, with aces starting on the foundation and only three passes through the waste allowed.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(3),
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      initialCards = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 5,
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
