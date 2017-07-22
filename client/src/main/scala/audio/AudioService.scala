package audio

import models.{MC, RequestMessage, SC, SP}
import phaser.PhaserGame

object AudioService {
  val keys = Seq(
    "draw" -> (0.0 -> 0.19),
    "shuffle" -> (0.19 -> 0.75),
    "playcard" -> (0.75 -> 0.94)
  )
}

class AudioService(p: PhaserGame) {
  private[this] val fx = p.add.audio("audio")

  AudioService.keys.foreach(k => fx.addMarker(k._1, k._2._1, k._2._2 - k._2._1))

  def onMove(msg: RequestMessage): Unit = if (p.getSettings.audio) {
    msg match {
      case _: SP => play("shuffle")
      case _: SC => play("draw")
      case _: MC => play("playcard")
      case _ => // noop
    }
  }

  def play(key: String) = {
    fx.play(key)
  }
}
