// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 7 (7 cards)
 *   Tableau cards face down (T0df): 101
 *   Empty tableau is filled with (T0f): 0 (Any card)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Similar to (like): interchange
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Breakwater extends GameRules(
  id = "breakwater",
  title = "Breakwater",
  like = Some("interchange"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/breakwater.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/alternations.htm"),
    Link("Solitaire City", "www.solitairecity.com/Alternations.shtml")
  ),
  description = "An easier variant of ^interchange^, in which we build regardless of suit.",
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
      initialCards = InitialCards.Count(7),
      cardsFaceDown = TableauFaceDownCards.EvenNumbered,
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any
    )
  )
)
