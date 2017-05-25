package input

import models.game.GameState
import models.pile.set.PileSet

class InputContextService(state: GameState, highlight: (Seq[String], Seq[Int]) => Unit) {
  private[this] def initialActive() = {
    val stockOpt = state.pileSets.find(_.behavior == PileSet.Behavior.Stock)
    val tableauOpt = stockOpt.orElse(state.pileSets.find(_.behavior == PileSet.Behavior.Tableau))
    val ps = tableauOpt.getOrElse(state.pileSets.head)
    val p = ps.piles(ps.piles.length / 2)
    val c = p.cards.lastOption
    (ps, p, c)
  }

  var (activePileSet, activePile, activeCard) = initialActive()

  private[this] def updateHighlight() = activeCard match {
    case Some(c) => highlight(Nil, Seq(c.id))
    case None => highlight(Seq(activePile.id), Nil)
  }

  def onInput(inputMessage: InputMessage) = {
    utils.Logging.info("Input Message: " + inputMessage)
    inputMessage match {
      case InputMessage.NextCard =>
        val (ps, p, c) = initialActive()
        activePileSet = ps
        activePile = p
        activeCard = c
        updateHighlight()
      case _ => utils.Logging.info(s"Unhandled input context message [$inputMessage].")
    }
    log()
  }

  private[this] def log() = {
    utils.Logging.info(s"Active context: [${activePileSet.behavior}:${activePile.id} / ${activeCard.map(_.toString).getOrElse("none")}]")
  }
}
