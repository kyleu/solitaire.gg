package models.pile.set

import models.pile.Pile

case class PileSet(behavior: String, piles: Seq[Pile], visible: Boolean) {
  for (p <- piles) {
    p.pileSet = Some(this)
  }

  var position = 0.0 -> 0.0
  var rows = 1
  var dimensions = 0.0 -> 0.0
  var divisor = 1.0
}
