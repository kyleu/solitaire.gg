// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Maximum deals from stock (maxdeals): 2 (2)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): inquisitor
 */
object LadyJane extends GameRules(
  id = "ladyjane",
  title = "Lady Jane",
  related = Seq("inquisitor"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lady_jane.htm")),
  description = "An easy two-deck ^spider^/^klondike^ blend by Thomas Warfield.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(2),
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.Aces
    )
  )
)
