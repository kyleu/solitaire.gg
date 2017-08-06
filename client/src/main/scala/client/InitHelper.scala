package client

import org.scalajs.dom
import org.scalajs.dom.raw.BeforeUnloadEvent
import settings.ThemeService
import util.{LogHelper, NullUtils}

import scala.scalajs.js

trait InitHelper { this: SolitaireGG =>
  protected[this] def init() = {
    LogHelper.init(debug = debug)
    logger.info("Solitaire.gg, v2.0.0")
    js.Dynamic.global.PhaserGlobal = js.Dynamic.literal("hideBanner" -> true)

    dom.window.onbeforeunload = (_: BeforeUnloadEvent) => {
      game match {
        case Some(_) => NullUtils.inst // TODO "You're playing a game. Are you sure you'd like to resign?"
        case _ => NullUtils.inst
      }
    }

    ThemeService.applyColorAndPattern(settings.getSettings.backgroundColor, settings.getSettings.backgroundPattern)

    phaser.start()
  }
}
