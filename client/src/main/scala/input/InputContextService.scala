package input

import models.game.GameState
import models.pile.set.PileSet
import util.Logging

class InputContextService(state: GameState, highlight: (Seq[String], Seq[Int]) => Unit) {
  private[this] def initialActive() = {
    val stockOpt = state.pileSets.find(_.behavior == PileSet.Behavior.Stock)
    val tableauOpt = stockOpt.orElse(state.pileSets.find(_.behavior == PileSet.Behavior.Tableau))
    val ps = tableauOpt.orElse(state.pileSets.headOption).getOrElse(throw new IllegalStateException("No pilesets available."))
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
    Logging.info("Input Message: " + inputMessage)
    inputMessage match {
      case InputMessage.NextCard => nextCard()
      case InputMessage.PreviousCard => previousCard()
      case _ => Logging.info(s"Unhandled input context message [$inputMessage].")
    }
    log()
  }

  private[this] def nextCard() = activeCard.foreach { c =>
    val next = activePile.cards.indexOf(c) match {
      case -1 => throw new IllegalStateException(s"Cannot find active card [$c] in active pile [${activePile.id}].")
      case x if x == activePile.cards.length - 1 => None
      case x => Some(activePile.cards(x + 1))
    }

    next.foreach { n =>
      activeCard = Some(n)
      updateHighlight()
    }
  }

  private[this] def previousCard() = activeCard.foreach { c =>
    val previous = activePile.cards.indexOf(c) match {
      case -1 => throw new IllegalStateException(s"Cannot find active card [$c] in active pile [${activePile.id}].")
      case x if x == 0 => None
      case x => Some(activePile.cards(x - 1))
    }

    previous.foreach { n =>
      activeCard = Some(n)
      updateHighlight()
    }
  }

  private[this] def log() = Logging.info(s"Active context: [${activePileSet.behavior}:${activePile.id} / ${activeCard.map(_.toString).getOrElse("none")}]")
}
