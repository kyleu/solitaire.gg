package phaser.playmat.layout

import models.pile.set.PileSet

object Layout {
  var margin = 0.7
  var padding = 0.2

  def calculateLayout(pileSets: Seq[PileSet], layout: String /* , aspectRatio */ ) = {
    val locations = collection.mutable.HashMap.empty[String, (Double, Double)]
    var xOffset = margin
    var yOffset = 0.6
    var remainingPileSets = pileSets
    var currentRowMaxHeight = 1.0
    var maxWidth = 0.0
    var lastChar = ' '
    var currentDivisor = 0

    def newRow() = {
      if (xOffset > maxWidth) {
        maxWidth = xOffset
      }
      xOffset = margin
      yOffset += currentRowMaxHeight
      currentRowMaxHeight = 1
    }

    def processCharacter(c: Char) = {
      val pileSet = c match {
        case 's' => remainingPileSets.find(_.behavior == "stock")
        case 'w' => remainingPileSets.find(_.behavior == "waste")
        case 'f' => remainingPileSets.find(_.behavior == "foundation")
        case 't' => remainingPileSets.find(_.behavior == "tableau")
        case 'c' => remainingPileSets.find(_.behavior == "cell")
        case 'r' => remainingPileSets.find(_.behavior == "reserve")
        case 'p' => remainingPileSets.find(_.behavior == "pyramid")
        case _ => None
      }
      c match {
        case ':' => xOffset += 1 + padding
        case '.' => xOffset += (1 + padding) / 2
        case '|' => newRow()
        case '2' => currentDivisor = 2
        case '3' => currentDivisor = 3
        case '4' => currentDivisor = 4
        case '5' => currentDivisor = 5
        case _ =>
          val ps = pileSet.getOrElse(throw new IllegalStateException(s"Unable to find set matching [$c]"))
          remainingPileSets = remainingPileSets.filterNot(_ == ps)
          // TODO ps.position = (xOffset - 0.5, yOffset - 0.5)
          var pileSetDimensions = LayoutDimensions.getDimensions(ps, currentDivisor.toDouble)
          if (!ps.visible) { // Hide this pile
            ps.piles.zipWithIndex.foreach { p =>
              locations(p._1.id) = ((p._2 * (1 + padding)) + 0.5, -10)
            }
          } else {
            if (pileSetDimensions._2 > currentRowMaxHeight) {
              currentRowMaxHeight = pileSetDimensions._2
            }
            if (ps.behavior == "pyramid") {
              var currentRow = 1
              var rowCounter = 0
              // TODO xOffset = margin + ((pileSet.rows - currentRow) / 2) * (1 + padding)
              ps.piles.foreach { pile =>
                if (rowCounter == currentRow) {
                  currentRow += 1
                  rowCounter -= rowCounter
                  // TODO xOffset = margin + ((pileSet.rows - currentRow) / 2) * (1 + padding)
                }

                locations(pile.id) = (xOffset, yOffset + ((currentRow - 1) * 0.5))
                xOffset = xOffset + 1 + padding
                rowCounter += 1
              }
            } else {
              var originalXOffset = xOffset
              var groupSize = ps.piles.length
              if (currentDivisor > 0) {
                groupSize = ps.piles.length / currentDivisor
              }
              ps.piles.zipWithIndex.foreach { p =>
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
    layout.foreach { char =>
      processCharacter(char);
    }
    newRow()

    (maxWidth - margin + padding, yOffset - 0.5, locations.toMap)
  }

}
