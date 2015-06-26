// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 5 (5 cards)
 *   Tableau cards face down (T0df): 101
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 5
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Similar to (like): unlimited
 *   Maximum deals from stock (maxdeals): 0
 *   Number of decks (ndecks): 1 (1 deck)
 */
object SingleInterchange extends GameRules(
  id = "singleinterchange",
  title = "Single Interchange",
  like = Some("unlimited"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/single_interchange.htm")),
  description = "A difficult one-deck variant of ^interchange^ invented by Thomas Warfield.",
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 5,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.EvenNumbered,
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
