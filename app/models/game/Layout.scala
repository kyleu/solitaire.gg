package models.game

import models.game.Layout.PileLocation

object Layout {
  case class PileLocation(id: String, x: Int, y: Int)
}

case class Layout(
  height: Int,
  width: Int,
  piles: List[PileLocation]
)
