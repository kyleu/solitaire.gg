package models.pile.set

import enumeratum.values._
import models.pile.Pile

object PileSet {
  sealed abstract class Behavior(val value: Char, val weight: Int) extends CharEnumEntry

  object Behavior extends CharEnum[Behavior] with CharCirceEnum[Behavior] {
    case object Stock extends Behavior('s', 0)
    case object Waste extends Behavior('w', 1)
    case object Pocket extends Behavior('k', 0)
    case object Foundation extends Behavior('f', 4)
    case object Tableau extends Behavior('t', 3)
    case object Cell extends Behavior('c', 2)
    case object Reserve extends Behavior('r', 2)
    case object Pyramid extends Behavior('p', 5)

    override val values = findValues

    val wtpf = Seq(Waste, Tableau, Pyramid, Foundation)
    val allButReserve = values.filterNot(_ == Reserve)
  }
}

case class PileSet(behavior: PileSet.Behavior, piles: IndexedSeq[Pile], visible: Boolean) {
  for (p <- piles) {
    p.pileSet = Some(this)
  }

  var position = 0.0 -> 0.0
  var rows = 1
  var dimensions = 0.0 -> 0.0
  var divisor = 1.0
}
