package audio

import phaser.PhaserGame

object AudioService {
  val keys = Seq(
    "autodrop" -> (1.491 -> 1.591),
    "move" -> (6.593 -> 6.618),
    "nomove" -> (6.818 -> 6.842),
    "redo" -> (7.042 -> 7.163),
    "drop" -> (5.519 -> 5.619),
    "flip" -> (6.239 -> 6.393),
    "undo" -> (7.976 -> 8.099),
    "deal01" -> (2.016 -> 2.167),
    "deal02" -> (2.367 -> 2.667),
    "deal04" -> (2.867 -> 3.468),
    "deal08" -> (3.668 -> 4.869),
    "areyousure" -> (0.000 -> 1.291),
    "startdrag" -> (7.363 -> 7.375),
    "autoflip" -> (1.791 -> 1.816),
    "turnwaste" -> (7.575 -> 7.776),
    "droppair" -> (5.819 -> 6.039),
    "dealwaste" -> (5.069 -> 5.319)
  )
}

class AudioService(p: PhaserGame) {
  private[this] val fx = p.add.audio("audio")

  AudioService.keys.foreach { k =>
    utils.Logging.info(s"Registering audio marker [${k._1}].")
    fx.addMarker(k._1, k._2._1, k._2._2 - k._2._1)
  }

  def play(key: String) = {
    fx.play(key)
  }
}
