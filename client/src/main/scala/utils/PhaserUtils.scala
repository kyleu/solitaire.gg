package utils

import com.definitelyscala.phaser.Signal

object PhaserUtils {
  def addToSignal(signal: Signal, x: => Unit) = signal.add(() => x, 0, 0.0)
}
