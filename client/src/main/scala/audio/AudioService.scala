package audio

import models.{MoveCards, RequestMessage, SelectCard, SelectPile}
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

  def onMove(msg: RequestMessage) = if (p.getSettings.audio) {
    msg match {
      case _: SelectPile => play("shuffle")
      case _: SelectCard => play("draw")
      case _: MoveCards => play("playcard")
      case _ => // noop
    }
  }

  def play(key: String) = {
    fx.play(key)
  }
}
