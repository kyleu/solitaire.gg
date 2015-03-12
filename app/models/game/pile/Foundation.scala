package models.game.pile

object Foundation {
  private val defaultOptions = PileOptions(
    cardsShown = Some(1),
    dragFromConstraint = Some("top-card-only"),
    dragToConstraint = Some("foundation")
  )
}

class Foundation(id: String, options: PileOptions = PileOptions.empty) extends Pile(id, "foundation", options = Foundation.defaultOptions.combine(options)) {
}
