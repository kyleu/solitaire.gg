// Generated rules for Solitaire.gg.
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
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 11
 *   Tableau suit match rule for building (T0s): 2 (In different suits)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): freecell
 *   Number of decks (ndecks): 2 (2 decks)
 *   Custom suits (suits): 35
 *   Enable super moves, whatever those are (supermoves): 1
 */
object ChineseFreeCell extends GameRules(
  id = "chinesefreecell",
  title = "Chinese FreeCell",
  like = Some("freecell"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/chinese_freecell.htm")),
  description = "A version of ^freecell^ played with only three suits.",
  deckOptions = DeckOptions(
    numDecks = 2,
    suits = Seq(Suit.Hearts, Suit.Spades, Suit.Diamonds)
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 11,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.DifferentSuits,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(CellRules())
)
