// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 26
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Number of decks (ndecks): 2 (2 decks)
 *   Allowed draws (ndraw): 1 (1)
 */
object Cromwell extends GameRules(
  id = "cromwell",
  title = "Cromwell",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/cromwell.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/cromwell.php")
  ),
  description = "A two-deck game with 26 tableau piles and a draw. Requires planning.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 26,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  special = Some(
    SpecialRules(
      shuffleBeforeRedeal = false,
      drawsAllowed = 1
    )
  )
)
