// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   *R0af (R0af): 6
 *   Reserve initial cards (R0d): 1
 *   Number of reserve piles (R0n): 4
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled from (T0fo): 64 (reserve)
 *   Tableau piles (T0n): 4
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object ShadyLanes extends GameRules(
  id = "shadylanes",
  title = "Shady Lanes",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/shady_lanes.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/shady_lanes.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/shady-lanes.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/ShadyLanes.htm")
  ),
  description = "A difficult game with four reserve piles and four tableau piles.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToEmptyFrom = Seq("reserve")
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 4,
      initialCards = 1,
      cardsFaceDown = -1
    )
  )
)
