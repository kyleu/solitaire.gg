// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): -1
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 6
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): threescompany
 *   Low card (lowpip): 4 (4)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): foursup
 *   Enable super moves, whatever those are (supermoves): 0
 */
object FoursUp extends GameRules(
  id = "foursup",
  title = "Fours Up",
  like = Some("threescompany"),
  related = Seq("foursup"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fours_up.htm")),
  description = "Thomas Warfield created this game as a continuation of the series starting with the traditional games ^busyaces^ and ^deuces^. The" +
    " number of tableau piles is again reduced, but now we can build regardless of suit so the game gets a bit easier.",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Rank.Four
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
      initialCards = 8,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      emptyFilledWith = FillEmptyWith.Aces
    )
  ),
  complete = false
)

