// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Empty tableau is filled with (T0f): 0 (Any card)
 *   Empty tableau is filled from (T0fo): 251
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Similar to (like): congress
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Dieppe extends GameRules(
  id = "dieppe",
  title = "Dieppe",
  like = Some("congress"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/dieppe.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/dieppe.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/dieppe.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/dieppe.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Dieppe.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/dieppe.htm")
  ),
  description = "A variation on ^congress^ where stacks may be moved, blanks can be filled by any card, and three rows of cards are dealt initially" +
    ". Almost every game seems winnable without any great difficulty.",
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
      numPiles = 8,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      mayMoveToEmptyFrom = Seq("stock", "pyramid", "waste", "pocket", "reserve", "cell", "foundation")
    )
  )
)
