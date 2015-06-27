// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Card initially dealt into cells (C0d): 2 (2 cards)
 *   Number of cells (C0n): 2
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): UUUUU DUUUU DDUUU DDDUU DDDDU DDDDU DDDUU DDUUU DUUUU UUUUU
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau rank match rule for moving stacks (T0tr): 8191 (Regardless of rank)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): russian
 */
object TenAcross extends GameRules(
  id = "tenacross",
  title = "Ten Across",
  like = Some("russian"),
  links = Seq(Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Ten_Across.html.en")),
  description = "A variation of ^russian^ with a different starting tableau and two cells, which start full.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUU",
        "DUUUU",
        "DDUUU",
        "DDDUU",
        "DDDDU",
        "DDDDU",
        "DDDUU",
        "DDUUU",
        "DUUUU",
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
