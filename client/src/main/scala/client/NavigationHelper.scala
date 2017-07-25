package client

import game.{GameListService, GameStartService}
import help.HelpService
import navigation.NavigationState
import settings.SettingsPanel

trait NavigationHelper { this: SolitaireGG =>
  private[this] def onNewGame(rules: Seq[String]) = {
    phaser.gameplay.activeGame.foreach { gameId =>
      GameStartService.endGame(this, gameId, win = false)
    }
    if (rules.headOption.contains("resign")) {
      navigation.resign()
    } else {
      navigation.play(rules)
    }
  }

  protected[this] def onStateChange(o: NavigationState, n: NavigationState, args: Seq[String]): Unit = {
    o match {
      case NavigationState.Loading => menu.setMenuPosition(settings.getSettings.menuPosition)
      case NavigationState.Settings => settings.applyAndSave(SettingsPanel.getCurrentSettings)
      case _ => // noop
    }
    n match {
      case NavigationState.Games => GameListService.update(game, onNewGame, menu)
      case NavigationState.Settings => SettingsPanel.initIfNeeded(settings.getSettings, menu)
      case NavigationState.Play => GameStartService.onGameStateChange(this, args)
      case NavigationState.Help => HelpService.show(args.headOption, menu)
      case _ => // noop
    }
  }
}
