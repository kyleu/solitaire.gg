package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 6
 *   Auto-move cards to foundation (F0a): 5 (When stackable cards are removable)
 *   Foundation initial cards (F0d): -1
 *   Maximum cards for foundation (F0m): 26
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   Initial card restriction (F0u): 2 (Unique suits)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 10 (10 cards)
 *   *T0db (T0db): 0
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): freecell
 *   Number of decks (ndecks): 2 (2 decks)
 *   Enable super moves, whatever those are (supermoves): 1
 */
object DoubleFreeCell extends GameRules(
  id = "doublefreecell",
  completed = true,
  title = "Double FreeCell",
  like = Some("freecell"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_freecell.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/freecell/double_freecell.htm")
  ),
  description = "Thomas Warfield's two-deck version of ^freecell^.",
  layout = "f:c|.t",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      maxCards = 26,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(10),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 6
    )
  )
)
