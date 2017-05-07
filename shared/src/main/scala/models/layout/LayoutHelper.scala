package models.layout

import models.pile.set.PileSet

class LayoutHelper(pileSets: Seq[PileSet], layout: String /* , aspectRatio */ ) {
  println("Layout Calculation!")
  var margin = 0.7
  var padding = 0.2

  def pileSetsForChar(remainingPileSets: Seq[PileSet], c: Char) = c match {
    case 's' => remainingPileSets.find(_.behavior == "stock")
    case 'w' => remainingPileSets.find(_.behavior == "waste")
    case 'f' => remainingPileSets.find(_.behavior == "foundation")
    case 't' => remainingPileSets.find(_.behavior == "tableau")
    case 'c' => remainingPileSets.find(_.behavior == "cell")
    case 'r' => remainingPileSets.find(_.behavior == "reserve")
    case 'p' => remainingPileSets.find(_.behavior == "pyramid")
    case _ => None
  }

  val locations = collection.mutable.HashMap.empty[String, (Double, Double)]
  var xOffset = margin
  var yOffset = 0.6
  var remainingPileSets = pileSets
  var currentRowMaxHeight = 1.0
  var maxWidth = 0.0
  var lastChar = ' '
  var currentDivisor = 0

  def newRow() = {
    if (xOffset > maxWidth) { maxWidth = xOffset }
    xOffset = margin
    yOffset += currentRowMaxHeight
    currentRowMaxHeight = 1
  }

  def processCharacter(c: Char) = {
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

        val pileSetDimensions = LayoutDimensions.getDimensions(pileSet, currentDivisor.toDouble)
        if (!pileSet.visible) { // Hide this pile
          pileSet.piles.zipWithIndex.foreach { p =>
            locations(p._1.id) = ((p._2 * (1 + padding)) + 0.5, -10)
          }
        } else {
          if (pileSetDimensions._2 > currentRowMaxHeight) {
            currentRowMaxHeight = pileSetDimensions._2
          }
          if (pileSet.behavior == "pyramid") {
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
        currentDivisor = 0
    }
    lastChar = c
  }

  val result = {
    layout.foreach(processCharacter)
    newRow()
    (maxWidth - margin + padding, yOffset - 0.5, locations.toMap)
  }
}
