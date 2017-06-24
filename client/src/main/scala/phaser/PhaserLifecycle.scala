package phaser

import msg.req.OnGameComplete
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
    onComplete('w', g)
  }

  def onLoss(g: PhaserGame) = {
    CardAnimation.loss(g.getPlaymat)
    onComplete('l', g)
  }

  private[this] def onComplete(status: Char, g: PhaserGame) = {
    val moves = g.gameplay.services.moves.getMoveCount
    val undos = g.gameplay.services.undo.undoCount
    val redos = g.gameplay.services.undo.redoCount
    OnGameComplete(
      id = g.gameplay.services.state.gameId,
      rules = g.gameplay.services.state.rules,
      seed = g.gameplay.services.state.seed,
      status = status,
      duration = 0L,
      moves = moves,
      undos = undos,
      redos = redos,
      score = g.gameplay.services.state.calculateScore(moves, undos, redos),
      firstMove = g.gameplay.services.moves.getFirstMove,
      occurred = System.currentTimeMillis
    )
  }
}
