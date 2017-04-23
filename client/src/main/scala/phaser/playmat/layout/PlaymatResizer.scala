package phaser.playmat.layout

import com.definitelyscala.phaser.Easing.Easing
import com.definitelyscala.phaser.Point
import phaser.playmat.Playmat

import scala.scalajs.js

class PlaymatResizer(p: Playmat) {
  def refreshLayout() = {
    var originalSize = p.w -> p.h

    // TODO p.layout = Layout.calculateLayout(p.pileSets, p.layoutString, p.game.world.width / p.game.world.height, p.game.menusVisible);

    // TODO p.w = p.layout.width * p.game.cardSet.cardWidth
    // TODO p.h = (p.layout.height - 0.1) * p.game.cardSet.cardHeight

    if (p.w != originalSize._1 || p.h != originalSize._2) {
      this.resize()
    }
  }

  def resize() = {
    var totalHeight = p.game.world.height
    var widthRatio = p.game.world.width / p.w
    var heightRatio = totalHeight / p.h

    var newPosition = p.position
    var newScale = p.scale

    if (widthRatio < heightRatio) {
      newScale = new Point(widthRatio, widthRatio)
      var yOffset = (totalHeight - (p.h * widthRatio)) / 2
      if (yOffset > 0 || p.y != 0) {
        newPosition = new Point(0, 0 /* yOffset */ )
      }
    } else {
      newScale = new Point(heightRatio, heightRatio)
      var xOffset = (p.game.world.width - (p.w * heightRatio)) / 2
      if (xOffset > 0 || p.x != 0) {
        newPosition = new Point(xOffset, 0)
      }
    }

    if (p.phaser.initialized) {
      val scaleProps = js.Dynamic.literal("x" -> newScale.x, "y" -> newScale.y)
      p.game.add.tween(p.scale).to(scaleProps, 500, Easing.Default, autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)

      val posProps = js.Dynamic.literal("x" -> newPosition.x, "y" -> newPosition.y)
      p.game.add.tween(p.position).to(posProps, 500, Easing.Default, autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
    } else {
      p.scale = newScale
      p.position = newPosition
    }
  }
}
