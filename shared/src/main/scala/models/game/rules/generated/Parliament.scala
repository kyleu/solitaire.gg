// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): -1
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 0 (Any card)
 *   Empty tableau is filled from (T0fo): 251
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Similar to (like): congress
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Parliament extends GameRules(
  id = "parliament",
  title = "Parliament",
  like = Some("congress"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/parliament.htm")),
  description = "An easier version of ^congress^, where the aces start on the foundation.",
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
      initialCards = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToEmptyFrom = Seq("stock", "pyramid", "waste", "pocket", "reserve", "cell", "foundation")
    )
  )
)
