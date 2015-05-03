// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object CastleOfIndolence extends GameRules(
  id = "castleofindolence",
  title = "Castle of Indolence",
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

