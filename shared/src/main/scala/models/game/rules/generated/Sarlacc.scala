// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Sarlacc extends GameRules(
  id = "sarlacc",
  title = "Sarlacc",
  description = "A ^freecell^ variant with a tableau of interlocking columns.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 6
    )
  ),
  pyramids = Seq(
    PyramidRules(
      name = "Tableau",
      suitMatchRuleForBuilding = SuitMatchRule.AlternatingColors,
      rankMatchRuleForBuilding = RankMatchRule.Down,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      mayMoveToEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      emptyFilledWith = PyramidFillEmptyWith.Aces
    )
  ),
  complete = false
)

