// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 11 (11 cards)
 *   Tableau cards face down (T0df): 101
 *   Empty tableau is filled with (T0f): 0 (Any card)
 *   Tableau piles (T0n): 11
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Similar to (like): tripleinterchange
 *   Maximum deals from stock (maxdeals): 0
 *   Number of decks (ndecks): 4 (4 decks)
 *   Related games (related): quadrupleinterchange
 */
object QuadrupleInterchange extends GameRules(
  id = "quadrupleinterchange",
  title = "Quadruple Interchange",
  like = Some("tripleinterchange"),
  related = Seq("quadrupleinterchange"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/quadruple_interchange.htm")),
  description = "A four-deck version of ^interchange^.",
  deckOptions = DeckOptions(
    numDecks = 4
  ),
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 11,
      initialCards = InitialCards.Count(11),
      cardsFaceDown = TableauFaceDownCards.EvenNumbered,
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.Aces
    )
  )
)
