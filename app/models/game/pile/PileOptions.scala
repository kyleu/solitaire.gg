package models.game.pile

object PileOptions {
  val empty = PileOptions()
}

case class PileOptions(
  cardsShown: Option[Int] = None,
  direction: Option[String] = None,

  selectCardConstraint: Option[String] = None,
  selectPileConstraint: Option[String] = None,
  dragFromConstraint: Option[String] = None,
  dragToConstraint: Option[String] = None
) {
  def combine(po: PileOptions) = {
    this.copy(
      cardsShown = if(po.cardsShown.isDefined) { po.cardsShown } else { cardsShown },
      direction = if(po.direction.isDefined) { po.direction } else { direction },

      selectCardConstraint = if(po.selectCardConstraint.isDefined) { po.selectCardConstraint } else { selectCardConstraint },
      selectPileConstraint = if(po.selectPileConstraint.isDefined) { po.selectPileConstraint } else { selectPileConstraint },
      dragFromConstraint = if(po.dragFromConstraint.isDefined) { po.dragFromConstraint } else { dragFromConstraint },
      dragToConstraint = if(po.dragToConstraint.isDefined) { po.dragToConstraint } else { dragToConstraint }
    )
  }
}
