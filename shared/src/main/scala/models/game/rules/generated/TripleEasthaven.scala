// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Tableau cards face down (T0df): 100
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Similar to (like): easthaven
 *   Number of decks (ndecks): 3 (3 decks)
 */
object TripleEasthaven extends GameRules(
  id = "tripleeasthaven",
  title = "Triple Easthaven",
  like = Some("easthaven"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triple_easthaven.htm")),
  description = "A three-deck version of ^easthaven^ by Thomas Warfield.",
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(3)
    )
  )
)
