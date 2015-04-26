package models.game.pile

import models.game.Rank
import models.game.pile.actions.SelectCardActions
import models.game.pile.constraints.Constraints

object PileOptionsHelper {
  val empty = PileOptions()

  val foundation = PileOptions(
    cardsShown = Some(1),
    dragFromConstraint = Some(Constraints.topCardOnly),
    dragToConstraint = Some(Constraints.klondikeFoundationDragTo)
  )

  val tableau = PileOptions(
    direction = Some("d"),
    selectCardConstraint = Some(Constraints.klondikeSelectCard),
    dragFromConstraint = Some(Constraints.klondikeDragFrom),
    dragToConstraint = Some(Constraints.klondikeTableauDragTo(Some(Rank.King))),
    selectCardAction = Some(SelectCardActions.klondike)
  )

  val waste = PileOptions(
    cardsShown = Some(3),
    direction = Some("r"),
    selectCardConstraint = Some(Constraints.klondikeSelectCard),
    dragFromConstraint = Some(Constraints.topCardOnly),
    selectCardAction = Some(SelectCardActions.klondike)
  )

  val reserve = waste.combine(PileOptions(cardsShown = Some(1)))
}
