// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 4
 *   Auto-move cards to foundation (F0a): 5 (When stackable cards are removable)
 *   Foundation low rank (F0b): 22 (Deck's high card)
 *   Foundation rank match rule (F0r): 32 (Build down)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   *T0db (T0db): 0
 *   Tableau piles (T0n): 8
 *   Tableau rank match rule for building (T0r): 128 (Build up)
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): freecell
 *   Enable super moves, whatever those are (supermoves): 1
 */
object InvertedFreeCell extends GameRules(
  id = "invertedfreecell",
  title = "Inverted FreeCell",
  like = Some("freecell"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/inverted_freecell.htm")),
  description = "Just like ^freecell^ but we limber up our brains by building everything in the opposite direction.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      rankMatchRule = RankMatchRule.Down,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      rankMatchRuleForBuilding = RankMatchRule.Up,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules())
)
