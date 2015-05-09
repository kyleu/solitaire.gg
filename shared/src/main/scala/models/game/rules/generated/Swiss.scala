// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): U DU DDU DDDU DDDDU DDDU DDU DU U
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Low card (lowpip): 2 (2)
 */
object Swiss extends GameRules(
  id = "swiss",
  title = "Swiss",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/swiss_patience.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/SwissPatience.htm")
  ),
  description = "Like ^klondike^ but aces are high and the tableau is pyramidical.",
  deckOptions = DeckOptions(
    lowRank = Rank.Two
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "DU",
        "DDU",
        "DDDU",
        "DDDDU",
        "DDDU",
        "DDU",
        "DU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.Kings
    )
  )
)
