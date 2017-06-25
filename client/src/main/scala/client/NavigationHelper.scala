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
      navigation.navigate(NavigationState.Games, allowSelf = true)
    } else {
      navigation.navigate(NavigationState.Play, rules)
    }
  }

  protected[this] def onStateChange(o: NavigationState, n: NavigationState, args: Seq[String]): Unit = {
    o match {
      case NavigationState.Loading => navigation.setMenuPosition(settings.getSettings.menuPosition)
      case NavigationState.Settings => settings.applyAndSave(SettingsPanel.getCurrentSettings)
      case _ => // noop
    }
    n match {
      case NavigationState.Games => GameListService.update(game, onNewGame, navigation)
      case NavigationState.Settings => SettingsPanel.initIfNeeded(settings.getSettings)
      case NavigationState.Play => GameStartService.onGameStateChange(this, args)
      case NavigationState.Help => HelpService.show(args.headOption)
      case _ => // noop
    }
  }
}
