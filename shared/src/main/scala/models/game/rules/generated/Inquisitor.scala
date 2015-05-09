// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Similar to (like): ladyjane
 *   Maximum deals from stock (maxdeals): 3 (3)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): quizzie
 */
object Inquisitor extends GameRules(
  id = "inquisitor",
  title = "Inquisitor",
  like = Some("ladyjane"),
  related = Seq("quizzie"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/inquisitor.htm")),
  description = "This ^ladyjane^ variation by Thomas Warfield requires you to build in suit, but gives you an extra redeal.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(3),
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
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.Aces
    )
  )
)
