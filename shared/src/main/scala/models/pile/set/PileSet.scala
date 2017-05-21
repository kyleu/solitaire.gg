package models.pile.set

import enumeratum.values._
import models.pile.Pile

object PileSet {
  sealed abstract class Behavior(val value: Char) extends CharEnumEntry

  object Behavior extends CharEnum[Behavior] with CharCirceEnum[Behavior] {
    case object Stock extends Behavior('s')
    case object Waste extends Behavior('w')
    case object Pocket extends Behavior('k')
    case object Foundation extends Behavior('f')
    case object Tableau extends Behavior('t')
    case object Cell extends Behavior('c')
    case object Reserve extends Behavior('r')
    case object Pyramid extends Behavior('p')

    override val values = findValues

    val wtpf = Seq(Waste, Tableau, Pyramid, Foundation)
    val allButReserve = values.filterNot(_ == Reserve)
  }
}

case class PileSet(behavior: PileSet.Behavior, piles: Seq[Pile], visible: Boolean) {
  for (p <- piles) {
    p.pileSet = Some(this)
  }

  var position = 0.0 -> 0.0
  var rows = 1
  var dimensions = 0.0 -> 0.0
  var divisor = 1.0
}
