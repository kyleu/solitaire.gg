package models.layout

import models.pile.Pile
import models.pile.set.PileSet

object LayoutDimensions {
  private[this] val padding = 0.2

  def getDimensions(pileSet: PileSet, divisor: Int) = {
    var ret = ((pileSet.piles.length / divisor) * (1 + padding), 1 + padding)
    pileSet.behavior match {
      case PileSet.Behavior.Waste =>
        val wasteCardsShown = pileSet.piles.headOption.getOrElse(throw new IllegalStateException).options.cardsShown
        if (wasteCardsShown.contains(3)) {
          ret = (2 * (1 + padding), ret._2)
        } else {
          ret = (1 + (wasteCardsShown.getOrElse(0) * padding * 1.5), ret._2)
        }
      case PileSet.Behavior.Reserve | PileSet.Behavior.Tableau =>
        def len(pile: Pile) = {
          val cardsShown = pile.options.cardsShown.getOrElse(0)
          if (cardsShown > 0 && cardsShown < pile.cards.length) {
            cardsShown
          } else {
            pile.cards.length
          }
        }

        val maxLen = pileSet.piles.map(len).max
        val overlappedCards = Math.max(maxLen, 1)
        if (divisor > 1) {
          ret = (ret._1 / divisor, 1 + (overlappedCards * padding))
        } else {
          ret = (ret._1, 1 + (overlappedCards * padding))
        }
      case PileSet.Behavior.Pyramid =>
        var rows = 1
        var rowCounter = 0
        pileSet.piles.foreach { _ =>
          if (rowCounter == rows) {
            rows += 1
            rowCounter -= rowCounter
          }

          rowCounter += 1
        }
        pileSet.rows = rows
        ret = (rows * (1 + padding), (rows * 0.5) + 0.5 + padding)
      case _ => // noop
    }

    pileSet.dimensions = ret
    pileSet.divisor = divisor.toDouble

    ret
  }

}
