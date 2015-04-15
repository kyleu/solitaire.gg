package models.game

case class PileLocation(id: String, x: Double, y: Double)

case class Layout(
  width: Double,
  height: Double,
  piles: List[PileLocation]
)
