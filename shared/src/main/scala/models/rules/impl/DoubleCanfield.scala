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
 *   Tableau piles (T0n): 5
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Low card (lowpip): -2 (?)
 *   Maximum deals from stock (maxdeals): 0
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): variegatedcanfield, demon
 */
object DoubleCanfield extends GameRules(
  id = "doublecanfield",
  completed = true,
  title = "Double Canfield",
  related = Seq("variegatedcanfield", "demon"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Double_Canfield_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_canfield.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/double_canfield.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/double_canfield.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/DoubleCanfield.htm")
  ),
  description = "An two-deck version of ^canfield^, much much easier than the original game.",
  layout = "swf|r:::.t",
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