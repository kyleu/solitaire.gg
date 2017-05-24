package input

import models.card.Card
import models.game.GameState
import models.pile.Pile
import models.pile.set.PileSet

class InputContextService() {
  private[this] var gameStateOpt: Option[GameState] = None

  private[this] var context: Option[(PileSet, Pile, Option[Card])] = None

  private[this] def initialContext(opt: Option[GameState]) = opt.foreach { gameState =>
    val stockOpt = gameState.pileSets.find(_.behavior == PileSet.Behavior.Stock)
    val tableauOpt = stockOpt.orElse(gameState.pileSets.find(_.behavior == PileSet.Behavior.Tableau))
    val ps = tableauOpt.getOrElse(gameState.pileSets.head)
    val p = ps.piles(ps.piles.length / 2)
    val c = p.cards.lastOption
    context = Some(ps, p, c)
  }

  def log() = context match {
    case Some(ctx) => utils.Logging.info(s"Active context: [${ctx._1.behavior}:${ctx._2.id} / ${ctx._3.map(_.toString).getOrElse("none")}]")
    case None => utils.Logging.info(s"Active context: [none]")
  }
}
