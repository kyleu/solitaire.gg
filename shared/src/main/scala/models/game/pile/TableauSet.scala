package models.game.pile

import models.game.Rank
import models.game.pile.actions.SelectCardActions
import models.game.pile.constraints.Constraints
import models.game.rules.TableauRules

object TableauSet {
  def apply(tableauRules: TableauRules): TableauSet = {
    val prefix = "tableau-"
    val options = getOptions(tableauRules)
    val piles = (1 to tableauRules.numPiles).map { i =>
      Pile(prefix + i, options)
    }
    new TableauSet(piles)
  }

  private[this] def getOptions(rules: TableauRules) = {
    PileOptions(
      direction = Some("d"),
      selectCardConstraint = Some(Constraints.klondikeSelectCard),
      dragFromConstraint = Some(Constraints.klondikeDragFrom),
      dragToConstraint = Some(Constraints.klondikeTableauDragTo(Some(Rank.King))),
      selectCardAction = Some(SelectCardActions.klondike)
    )
  }
}

class TableauSet(piles: Seq[Pile]) extends PileSet("tableau", piles)
