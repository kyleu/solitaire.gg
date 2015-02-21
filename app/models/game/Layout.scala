package models.game

case class PileLocation(id: String, x: Int, y: Int)

object Layout {
  def klondike = Layout(
    width = 1640,
    height = 800,
    piles = List(
      PileLocation("stock", 30, 30),
      PileLocation("waste", 260, 30),

      PileLocation("foundation-1", 720, 30),
      PileLocation("foundation-2", 950, 30),
      PileLocation("foundation-3", 1180, 30),
      PileLocation("foundation-4", 1410, 30),

      PileLocation("tableau-1", 30, 360),
      PileLocation("tableau-2", 260, 360),
      PileLocation("tableau-3", 490, 360),
      PileLocation("tableau-4", 720, 360),
      PileLocation("tableau-5", 950, 360),
      PileLocation("tableau-6", 1180, 360),
      PileLocation("tableau-7", 1410, 360)
    )
  )

  def sandbox = Layout(
    width = 1720,
    height = 800,
    piles = List(
      PileLocation("sandbox-1", 860, 400)
    )
  )
}

case class Layout(
  width: Int,
  height: Int,
  piles: List[PileLocation]
)
