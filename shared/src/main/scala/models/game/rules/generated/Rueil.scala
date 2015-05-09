// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 5 (5 cards)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Similar to (like): malmaison
 *   Maximum deals from stock (maxdeals): 2 (2)
 *   Number of decks (ndecks): 3 (3 decks)
 */
object Rueil extends GameRules(
  id = "rueil",
  title = "Rueil",
  like = Some("malmaison"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/rueil.htm")),
  description = "A version of ^malmaison^ made easier by allowing a redeal.",
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.Aces
    )
  )
)
