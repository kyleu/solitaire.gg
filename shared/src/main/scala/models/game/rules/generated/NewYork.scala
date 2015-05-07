// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled from (T0fo): BIT_STOCK|BIT_WASTE
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 3
 *   Deal cards from stock (dealto): 7 (Manually)
 *   Low card (lowpip): -2 (?)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): gotham
 */
object NewYork extends GameRules(
  id = "newyork",
  title = "New York",
  related = Seq("gotham"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/new_york.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/new_york.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/new_york.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/new-york.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/NewYork.htm")
  ),
  description = "In this variation of ^dover^, you can choose which of the three waste piles you play cards from the stock onto, which is good beca" +
    "use it's hard to rearrange things much on the tableau.",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Rank.Unknown
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Manually,
      maximumDeals = Some(1)
    )
  ),
  waste = Some(
    WasteRules(
      numPiles = 3
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Aces,
      mayMoveToEmptyFrom = Seq("Stock", "Waste")
    )
  ),
  complete = false
)

