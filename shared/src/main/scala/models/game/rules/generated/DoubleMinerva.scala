// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Reserve initial cards (R0d): 13
 *   Reserve cards face down (R0df): 0
 *   Number of reserve piles (R0n): 1
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau cards face down (T0df): 102
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 1 (One by one)
 *   Similar to (like): minerva
 *   Maximum deals from stock (maxdeals): 2 (2)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object DoubleMinerva extends GameRules(
  id = "doubleminerva",
  title = "Double Minerva",
  like = Some("minerva"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_minerva.htm")),
  description = "Thomas Warfield's two-deck version of ^minerva^.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 13,
      cardsFaceDown = 0
    )
  )
)
