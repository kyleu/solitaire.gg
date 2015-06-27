// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Auto-fill an empty tableau from (T0af): 6 (First waste then stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): busyaces
 *   Number of decks (ndecks): 2 (2 decks)
 *   Enable super moves, whatever those are (supermoves): 0
 */
object Courtyard extends GameRules(
  id = "courtyard",
  title = "Courtyard",
  like = Some("busyaces"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/courtyard.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/courtyard.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/courtyard.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/courtyard.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Courtyard.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/courtyard.html")
  ),
  description = "A variation of ^busyaces^ which increases the difficulty by autofilling empty tableau spaces the waste and stock.",
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
      numPiles = 12,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock
    )
  )
)
