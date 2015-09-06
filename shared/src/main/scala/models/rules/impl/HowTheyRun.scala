// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Card initially dealt into cells (C0d): 2 (2 cards)
 *   Number of cells (C0n): 2
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation add complete sequences only (F0cs): true
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): DDDUU DDDUU DDDUU UUUUU UUUUU UUUUU UUUUU UUUUU UUUUU UUUUU
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau rank match rule for moving stacks (T0tr): 8191 (Regardless of rank)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 */
object HowTheyRun extends GameRules(
  id = "howtheyrun",
  completed = true,
  title = "How They Run",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/how_they_run.htm")),
  description = "A variation of ^threeblindmice^ invented by Erik den Hollander with two cells replacing the two card reserve.",
  layout = "f::c|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDUU",
        "DDDUU",
        "DDDUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 2,
      initialCards = 2
    )
  )
)
