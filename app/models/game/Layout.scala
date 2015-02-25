package models.game

case class PileLocation(id: String, x: Double, y: Double)

object Layout {
  def klondike = Layout(
    width = 7.8,
    height = 5.0,
    piles = List(
      PileLocation("stock", 0.1, 0.1),
      PileLocation("waste", 1.2, 0.1),

      PileLocation("foundation-1", 3.4, 0.1),
      PileLocation("foundation-2", 4.5, 0.1),
      PileLocation("foundation-3", 5.6, 0.1),
      PileLocation("foundation-4", 6.7, 0.1),

      PileLocation("tableau-1", 0.1, 1.2),
      PileLocation("tableau-2", 1.2, 1.2),
      PileLocation("tableau-3", 2.3, 1.2),
      PileLocation("tableau-4", 3.4, 1.2),
      PileLocation("tableau-5", 4.5, 1.2),
      PileLocation("tableau-6", 5.6, 1.2),
      PileLocation("tableau-7", 6.7, 1.2)
    )
  )

  def sandbox = Layout(
    width = 8.0,
    height = 6.0,
    piles = List(
      PileLocation("sandbox-1", 3.5, 2.5)
    )
  )
}

case class Layout(
  width: Double,
  height: Double,
  piles: List[PileLocation]
)
