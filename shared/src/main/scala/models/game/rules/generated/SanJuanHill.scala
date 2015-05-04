// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): Hill
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): -1
 *   Tableau name (T0Nm): Rough Riders
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Similar to (like): fortythieves
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): robie, napoleonsquadrilateral, famousfifty, fortybandits, limited, elba, threepi...
 */
object SanJuanHill extends GameRules(
  id = "sanjuanhill",
  title = "San Juan Hill",
  like = Some("fortythieves"),
  related = Seq(
    "robie", "napoleonsquadrilateral", "famousfifty", "fortybandits", "limited", "elba", "threepirates", "squadron",
    "fortythieves3", "sixtythieves", "littlenapoleon", "eightythieves", "mamysusan", "sanjuanhill", "fortythieves4", "thievesrush",
    "josephine"
  ),
  description = "An easier variant of ^fortythieves^ in which aces are already on the foundation.",
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
      name = "Hill",
      numPiles = 8,
      initialCards = 8,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Rough Riders",
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

