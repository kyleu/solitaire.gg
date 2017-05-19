package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 9 (9 cards)
 *   Tableau cards face down (T0df): 101
 *   Empty tableau is filled with (T0f): 0 (Any card)
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Similar to (like): interchange
 *   Maximum deals from stock (maxdeals): 0
 *   Number of decks (ndecks): 3 (3 decks)
 *   Related games (related): quadrupleinterchange
 */
object TripleInterchange extends GameRules(
  id = "tripleinterchange",
  completed = true,
  title = "Triple Interchange",
  like = Some("interchange"),
  related = Seq("quadrupleinterchange"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triple_interchange.htm")),
  layout = ":::::sw|f|::t",
  deckOptions = DeckOptions(numDecks = 3),
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(9),
      cardsFaceDown = TableauFaceDownCards.EvenNumbered,
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
