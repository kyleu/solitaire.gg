// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Pyramid name (P0Nm): Tableau
 *   *P0ds (P0ds): ++==
 *   Empty pyramid is filled with (P0f): 1 (Kings only)
 *   *P0n (P0n): 9
 *   Pyramid suit match rule for building (P0s): 4 (In alternating colors)
 *   Pyramid rank match rule for moving stacks (P0tr): 8191 (Regardless of rank)
 *   Pyramid suit match rule for moving stacks (P0ts): 5 (Regardless of suit)
 *   Number of pyramids (Pn): 1 (1 pyramid)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau sets (Tn): 0 (0 tableau sets)
 *   Number of waste piles (W0n): 0
 */
object LoveADuck extends GameRules(
  id = "loveaduck",
  title = "Love a Duck",
  description = "A ^yukon^-type game, played on an interlocking tableau.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  pyramids = Seq(
    PyramidRules(
      name = "Tableau",
      suitMatchRuleForBuilding = SuitMatchRule.AlternatingColors,
      rankMatchRuleForBuilding = RankMatchRule.Down,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      mayMoveToNonEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      mayMoveToEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
