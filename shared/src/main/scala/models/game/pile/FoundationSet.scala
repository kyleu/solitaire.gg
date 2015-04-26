package models.game.pile

import models.game.pile.constraints.Constraints
import models.game.rules.FoundationRules

object FoundationSet {
  def apply(foundationRules: FoundationRules): FoundationSet = {
    val prefix = "foundation-"
    val options = getOptions(foundationRules)
    val piles = (1 to foundationRules.numPiles).map { i =>
      Pile(prefix + i, options)
    }
    new FoundationSet(piles)
  }

  private[this] def getOptions(rules: FoundationRules) = {
    PileOptions(
      cardsShown = Some(1),
      dragFromConstraint = Some(Constraints.topCardOnly),
      dragToConstraint = Some(Constraints.klondikeFoundationDragTo)
    )
  }
}

class FoundationSet(piles: Seq[Pile]) extends PileSet("foundation", piles)
