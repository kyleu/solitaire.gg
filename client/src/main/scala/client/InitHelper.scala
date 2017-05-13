package client

import org.scalajs.dom
import org.scalajs.dom.raw.BeforeUnloadEvent
import settings.ThemeService
import utils.{Logging, NullUtils}

import scala.scalajs.js

trait InitHelper { this: SolitaireGG =>
  protected[this] def init() = {
    utils.Logging.info("Solitaire.gg, v2.0.0")
    Logging.installErrorHandler()
    js.Dynamic.global.PhaserGlobal = js.Dynamic.literal("hideBanner" -> true)

    dom.window.onbeforeunload = (e: BeforeUnloadEvent) => {
      game match {
        case Some(_) => NullUtils.inst //"You're playing a game. Are you sure you'd like to resign?"
        case _ => NullUtils.inst
      }
    }

    ThemeService.applyColorAndPattern(settings.getSettings.backgroundColor, settings.getSettings.backgroundPattern)

    phaser.start()
  }
}
