package models.layout

import models.pile.set.PileSet

class LayoutHelper(pileSets: Seq[PileSet], layout: String /* , aspectRatio */ ) {
  private[this] val margin = 0.7
  private[this] val padding = 0.2

  def pileSetsForChar(remainingPileSets: Seq[PileSet], c: Char) = PileSet.Behavior.withValueOpt(c).flatMap { behavior =>
    remainingPileSets.find(_.behavior == behavior)
  }

  private[this] val locations = collection.mutable.HashMap.empty[String, (Double, Double)]
  private[this] var xOffset = margin
  private[this] var yOffset = 0.6
  private[this] var remainingPileSets = pileSets
  private[this] var currentRowMaxHeight = 1.0
  private[this] var maxWidth = 0.0
  private[this] var lastChar = ' '
  private[this] var currentDivisor = 1

  private[this] def newRow() = {
    if (xOffset > maxWidth) { maxWidth = xOffset }
    xOffset = margin
    yOffset += currentRowMaxHeight
    currentRowMaxHeight = 1
  }

  private[this] def processCharacter(c: Char) = {
    val pile = pileSetsForChar(remainingPileSets, c)
    c match {
      case ':' => xOffset += 1 + padding
      case '.' => xOffset += (1 + padding) / 2
      case '|' => newRow()
      case '2' => currentDivisor = 2
      case '3' => currentDivisor = 3
      case '4' => currentDivisor = 4
      case '5' => currentDivisor = 5
      case _ =>
        val pileSet = pile.getOrElse(throw new IllegalStateException(s"Unable to find set matching [$c]"))
        remainingPileSets = remainingPileSets.filterNot(_ == pileSet)
        pileSet.position = (xOffset - 0.5) -> (yOffset - 0.5)

        val pileSetDimensions = LayoutDimensions.getDimensions(pileSet, currentDivisor)
        if (!pileSet.visible) { // Hide this pile
          pileSet.piles.zipWithIndex.foreach { p =>
            locations(p._1.id) = ((p._2 * (1 + padding)) + 0.5, -10)
          }
        } else {
          if (pileSetDimensions._2 > currentRowMaxHeight) {
            currentRowMaxHeight = pileSetDimensions._2
          }
          if (pileSet.behavior == PileSet.Behavior.Pyramid) {
            var currentRow = 1
            var rowCounter = 0
            xOffset = margin + ((pileSet.rows.toDouble - currentRow) / 2) * (1 + padding)
            pileSet.piles.foreach { pile =>
              if (rowCounter == currentRow) {
                currentRow += 1
                rowCounter -= rowCounter
                xOffset = margin + ((pileSet.rows.toDouble - currentRow) / 2) * (1 + padding)
              }
              locations(pile.id) = (xOffset, yOffset + ((currentRow - 1) * 0.5))
              xOffset = xOffset + 1 + padding
              rowCounter += 1
            }
          } else {
            val originalXOffset = xOffset
            var groupSize = pileSet.piles.length
            if (currentDivisor > 0) { groupSize = pileSet.piles.length / currentDivisor }
            pileSet.piles.zipWithIndex.foreach { p =>
              if (p._2 > 0 && p._2 % groupSize == 0) {
                newRow()
                currentRowMaxHeight = pileSetDimensions._2
              }
              locations(p._1.id) = xOffset -> yOffset
              xOffset = xOffset + 1 + padding
            }
            xOffset = originalXOffset + pileSetDimensions._1
          }
        }
        currentDivisor = 1
    }
    lastChar = c
  }

  val result = {
    layout.foreach(processCharacter)
    newRow()
    (maxWidth - margin + padding, yOffset - 0.5, locations.toMap)
  }
}
