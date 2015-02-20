package models.game

case class PileLocation(id: String, x: Int, y: Int)

object Layout {
  def klondike = Layout(
    width = 1540,
    height = 800,
    piles = List(
      PileLocation("stock", 10, 10),
      PileLocation("waste", 230, 10),

      PileLocation("foundation-1", 670, 10),
      PileLocation("foundation-2", 890, 10),
      PileLocation("foundation-3", 1110, 10),
      PileLocation("foundation-4", 1330, 10),

      PileLocation("tableau-1", 10, 320),
      PileLocation("tableau-2", 230, 320),
      PileLocation("tableau-3", 450, 320),
      PileLocation("tableau-4", 670, 320),
      PileLocation("tableau-5", 890, 320),
      PileLocation("tableau-6", 1110, 320),
      PileLocation("tableau-7", 1330, 320)
    )
  )

  def sandbox = Layout(
    width = 200,
    height = 300,
    piles = List(
      PileLocation("sandbox-1", 0, 0)
    )
  )
}

case class Layout(
  width: Int,
  height: Int,
  piles: List[PileLocation]
)
