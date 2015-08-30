// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Reserve initial cards (R0d): 1
 *   Number of reserve piles (R0n): 7
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 0
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 5
 *   Low card (lowpip): -2 (?)
 */
object AgnesBernauer extends GameRules(
  id = "agnesbernauer",
  completed = false,
  title = "Agnes Bernauer",
  links = Seq(
    Link("Solitaire Central", "www.solitairecentral.com/rules/AgnesBernauer.html"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Agnes_(card_game)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/agnes_bernauer.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/agnes.html")
  ),
  description = "A variation on ^klondike^ with seven reserves.",
  layout = "sf|r|t",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Reserve,
      maximumDeals = Some(1)
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
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 7,
      initialCards = 1,
      cardsFaceDown = -1
    )
  )
)