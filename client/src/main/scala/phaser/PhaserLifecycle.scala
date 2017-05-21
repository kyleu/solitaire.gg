package phaser

import msg.req.{OnGameLost, OnGameWon, SocketRequestMessage}
import org.scalajs.dom
import phaser.card.CardAnimation
import phaser.state.{InitialState, LoadingState}

object PhaserLifecycle {
  def start(g: PhaserGame) = {
    g.state.add("initialState", new InitialState())
    g.state.add("loading", new LoadingState(g.getSettings))
    g.state.add("gameplay", g.gameplay)

    dom.window.onresize = g.resize _
    g.state.start("initialState", clearWorld = false, clearCache = false)
  }

  def onWin(g: PhaserGame) = {
    CardAnimation.win(g.getPlaymat)
    OnGameWon(
      id = g.gameplay.services.state.gameId,
      duration = 0L,
      moves = g.gameplay.services.moves.getMoveCount,
      undos = g.gameplay.services.undo.undoCount,
      redos = g.gameplay.services.undo.redoCount,
      firstMove = 0L,
      occurred = System.currentTimeMillis
    )
  }

  def onLoss(g: PhaserGame) = {
    CardAnimation.loss(g.getPlaymat)
    OnGameLost(
      id = g.gameplay.services.state.gameId,
      duration = 0L,
      moves = g.gameplay.services.moves.getMoveCount,
      undos = g.gameplay.services.undo.undoCount,
      redos = g.gameplay.services.undo.redoCount,
      firstMove = 0L,
      occurred = System.currentTimeMillis
    )
  }
}
