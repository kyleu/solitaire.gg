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
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): freecell
 *   Low card (lowpip): -2 (?)
 *   Related games (related): sixbyfour, ephemeralfreecell, challengefreecell, antares, sevenbyfive, spidercel...
 *   Enable super moves, whatever those are (supermoves): 1
 */
object SelectiveFreeCell extends GameRules(
  id = "selectivefreecell",
  title = "Selective FreeCell",
  like = Some("freecell"),
  related = Seq(
    "sixbyfour", "ephemeralfreecell", "challengefreecell", "antares", "sevenbyfive", "spidercells", "bigfreecell", "chinesefreecell",
    "sevenbyfour", "invertedfreecell", "selectivefreecell", "doublefreecell", "freecellduplex"
  ),
  description = "A variation of ^freecell^ where the first card played to the foudnation sets the base value for all the foundations.",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
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
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(CellRules()),
  complete = false
)

