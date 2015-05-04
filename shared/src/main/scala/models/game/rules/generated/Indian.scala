// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Tableau cards face down (T0df): 1
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 2 (In different suits)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Indian extends GameRules(
  id = "indian",
  title = "Indian",
  description = "An easy game that is similar to ^fortythieves^, except that the first card in each stack of the 10 by 3 tableau is face down, and " +
  "cards can be played on any suit other than their own.",
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
      numPiles = 8,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(1),
      suitMatchRuleForBuilding = SuitMatchRule.DifferentSuits,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

