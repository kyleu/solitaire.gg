// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 6 (6 cards)
 *   Tableau piles (T0n): 14
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Similar to (like): fortythieves
 *   Number of decks (ndecks): 4 (4 decks)
 *   Related games (related): robie, napoleonsquadrilateral, famousfifty, fortybandits, limited, elba, threepi...
 */
object FortyThieves4Deck extends GameRules(
  id = "fortythieves4",
  title = "Forty Thieves (4 deck)",
  like = Some("fortythieves"),
  related = Seq(
    "robie", "napoleonsquadrilateral", "famousfifty", "fortybandits", "limited", "elba", "threepirates", "squadron",
    "fortythieves3", "sixtythieves", "littlenapoleon", "eightythieves", "mamysusan", "sanjuanhill", "fortythieves4", "thievesrush",
    "josephine"
  ),
  description = "A four deck version of ^fortythieves^ with a 14 by 6 tableau.  You will need a large screen to play this game.",
  deckOptions = DeckOptions(
    numDecks = 4
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
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
      numPiles = 14,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

