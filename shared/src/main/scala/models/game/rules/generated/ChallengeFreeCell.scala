// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 4
 *   Auto-move cards to foundation (F0a): 5 (When stackable cards are removable)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   *T0db (T0db): 0
 *   Piles with low cards at bottom (T0dc): 8
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): freecell
 *   Related games (related): superchallengefreecell
 *   Enable super moves, whatever those are (supermoves): 1
 */
object ChallengeFreeCell extends GameRules(
  id = "challengefreecell",
  title = "Challenge FreeCell",
  like = Some("freecell"),
  related = Seq("superchallengefreecell"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/challenge_freecell.htm"),
    Link("Michael Keller's amazing FreeCell FAQ", "solitairelaboratory.com/fcfaq.html#AceDepth")
  ),
  description = "A version of ^freecell^ invented by Thomas Warfield where the aces and twos are always at the bottoms of the eight stacks.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Aces,
      pilesWithLowCardsAtBottom = 8
    )
  ),
  cells = Some(CellRules()),
  complete = false
)

