// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 4 (Keeping piles level)
 *   Foundation initial cards (F0d): 0 (None)
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Reserve initial cards (R0d): 13
 *   Number of reserve piles (R0n): 4
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): beleagueredcastle
 *   Number of decks (ndecks): 2 (2 decks)
 *   Enable super moves, whatever those are (supermoves): 1
 */
object CastleOfIndolence extends GameRules(
  id = "castleofindolence",
  title = "Castle of Indolence",
  like = Some("beleagueredcastle"),
  description = "Thomas Warfield's adaptation of a 19th century game first described in George A. Bonaventure's 1932 book of solitaire games. It is" +
  " two-deck game where 52 cards start on the tableau and another 52 start in the reserve. Suits of cards are completely ignored.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      suitMatchRule = SuitMatchRule.Any,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 4,
      initialCards = 13,
      cardsFaceDown = 100
    )
  ),
  complete = false
)

