// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 6
 *   Auto-move cards to foundation (F0a): 5 (When stackable cards are removable)
 *   Foundation initial cards (F0d): -1
 *   Maximum cards for foundation (F0m): 2*13
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   TODO (F0u): 2
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 10 (10 cards)
 *   *T0db (T0db): 0
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): freecell
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): sixbyfour, ephemeralfreecell, challengefreecell, antares, sevenbyfive, spidercel...
 *   Enable super moves, whatever those are (supermoves): 1
 */
object DoubleFreeCell extends GameRules(
  id = "doublefreecell",
  title = "Double FreeCell",
  like = Some("freecell"),
  related = Seq(
    "sixbyfour", "ephemeralfreecell", "challengefreecell", "antares", "sevenbyfive", "spidercells", "bigfreecell", "chinesefreecell",
    "sevenbyfour", "invertedfreecell", "selectivefreecell", "doublefreecell", "freecellduplex"
  ),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_freecell.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/freecell/double_freecell.htm")
  ),
  description = "Thomas Warfield's two-deck version of ^freecell^.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      wrapFromKingToAce = true,
      maxCards = 26,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(10),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 6
    )
  ),
  complete = false
)

