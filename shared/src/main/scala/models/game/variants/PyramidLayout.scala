package models.game.variants

import models.game.{ PileLocation, Layout }

object PyramidLayout {
  val layout = Layout(
    width = 7.8,
    height = 4.3,
    piles = List(
      PileLocation("graveyard", -1.0, -1.0),

      PileLocation("stock", 3.3, 3.6),
      PileLocation("waste", 4.4, 3.6),

      PileLocation("pile-1-1", 3.9, 0.7),

      PileLocation("pile-2-1", 3.3, 1.0),
      PileLocation("pile-2-2", 4.4, 1.0),

      PileLocation("pile-3-1", 2.8, 1.3),
      PileLocation("pile-3-2", 3.9, 1.3),
      PileLocation("pile-3-3", 5.0, 1.3),

      PileLocation("pile-4-1", 2.2, 1.6),
      PileLocation("pile-4-2", 3.3, 1.6),
      PileLocation("pile-4-3", 4.4, 1.6),
      PileLocation("pile-4-4", 5.5, 1.6),

      PileLocation("pile-5-1", 1.7, 1.9),
      PileLocation("pile-5-2", 2.8, 1.9),
      PileLocation("pile-5-3", 3.9, 1.9),
      PileLocation("pile-5-4", 5.0, 1.9),
      PileLocation("pile-5-5", 6.1, 1.9),

      PileLocation("pile-6-1", 1.1, 2.2),
      PileLocation("pile-6-2", 2.2, 2.2),
      PileLocation("pile-6-3", 3.3, 2.2),
      PileLocation("pile-6-4", 4.4, 2.2),
      PileLocation("pile-6-5", 5.5, 2.2),
      PileLocation("pile-6-6", 6.6, 2.2),

      PileLocation("pile-7-1", 0.6, 2.5),
      PileLocation("pile-7-2", 1.7, 2.5),
      PileLocation("pile-7-3", 2.8, 2.5),
      PileLocation("pile-7-4", 3.9, 2.5),
      PileLocation("pile-7-5", 5.0, 2.5),
      PileLocation("pile-7-6", 6.1, 2.5),
      PileLocation("pile-7-7", 7.2, 2.5)
    )
  )
}
