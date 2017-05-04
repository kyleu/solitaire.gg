package phaser.playmat

import com.definitelyscala.phaser.Point
import models.layout.Layout
import settings.MenuPosition

import scala.scalajs.js

class PlaymatResizer(p: Playmat) {
  private[this] var lastSize = p.phaser.width -> p.phaser.height

  def refreshLayout() = {
    val originalSize = 0.0 -> 0.0

    p.layout = Layout.calculateLayout(p.pileSets, p.layoutString)

    p.w = p.layout._1 * p.phaser.getSettings.cardSet.cardWidth
    p.h = (p.layout._2 - 0.1) * p.phaser.getSettings.cardSet.cardHeight

    if (p.w != originalSize._1 || p.h != originalSize._2) {
      this.resize()
    }
  }

  def resizeIfChanged() = if (lastSize._1 != p.phaser.width || this.lastSize._2 != p.phaser.height) {
    resize()
  }

  def resize() = {
    val totalWidth = p.game.world.width
    val widthRatio = totalWidth / p.w

    val (totalHeight, menuHeight) = if (p.phaser.getSettings.menuPosition != MenuPosition.Hidden) {
      (p.game.world.height - 112) -> 56
    } else {
      p.game.world.height -> 0
    }

    val heightRatio = totalHeight / p.h

    this.lastSize = totalWidth -> totalHeight

    var newPosition = p.position
    var newScale = p.scale

    if (widthRatio < heightRatio) {
      newScale = new Point(widthRatio, widthRatio)
      val yOffset = (totalHeight - (p.h * widthRatio)) / 2
      if (yOffset > 0 || p.y != menuHeight) {
        newPosition = new Point(0, menuHeight.toDouble /* + yOffset */ )
      }
    } else {
      newScale = new Point(heightRatio, heightRatio)
      val xOffset = (p.game.world.width - (p.w * heightRatio)) / 2
      if (xOffset > 0 || p.x != 0) {
        newPosition = new Point(xOffset, menuHeight.toDouble)
      }
    }

    if (p.phaser.initialized) {
      val scaleProps = js.Dynamic.literal("x" -> newScale.x, "y" -> newScale.y)
      p.game.add.tween(p.scale).to(scaleProps, 500, "Quad.easeInOut", autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)

      val posProps = js.Dynamic.literal("x" -> newPosition.x, "y" -> newPosition.y)
      p.game.add.tween(p.position).to(posProps, 500, "Quad.easeInOut", autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
    } else {
      p.scale = newScale
      p.position = newPosition
    }
  }
}
