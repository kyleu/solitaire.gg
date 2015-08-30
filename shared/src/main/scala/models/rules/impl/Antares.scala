// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 4
 *   Auto-move cards to foundation (F0a): 4 (Keeping piles level)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau name (T0Nm): Left Tableau
 *   Tableau initial cards (T0d): 7 (7 cards)
 *   *T0db (T0db): 0
 *   Tableau piles (T0n): 4
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   *T0sc (T0sc): false
 *   Tableau name (T1Nm): Right Tableau
 *   Tableau initial cards (T1d): 6 (6 cards)
 *   Empty tableau is filled with (T1f): 1 (Kings only)
 *   Tableau piles (T1n): 4
 *   Tableau suit match rule for building (T1s): 1 (In same suit)
 *   *T1sc (T1sc): false
 *   Tableau rank match rule for moving stacks (T1tr): 8191 (Regardless of rank)
 *   Tableau suit match rule for moving stacks (T1ts): 5 (Regardless of suit)
 *   Tableau sets (Tn): 2 (2 tableau sets)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): freecell
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Antares extends GameRules(
  id = "antares",
  completed = false,
  title = "Antares",
  like = Some("freecell"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/antares.htm")),
  description = "Thomas Warfield's combination of ^freecell^ and ^scorpion^ divides the tableau into two halves, one where we build in alternate co" +
    "lors and move cards by FreeCell rules, one where we build in the same suit and move by Scorpion rules.",
  layout = "f|c|tt",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Left Tableau",
      numPiles = 4,
      initialCards = InitialCards.Count(7),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    ),
    TableauRules(
      name = "Right Tableau",
      setNumber = 1,
      numPiles = 4,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(CellRules())
)