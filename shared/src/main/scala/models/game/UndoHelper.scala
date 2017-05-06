package models.game

import models._

class UndoHelper() {
  var undoCount = 0
  var redoCount = 0

  val historyQueue = collection.mutable.Stack[ReversibleResponseMessage]()
  val undoneQueue = collection.mutable.Stack[ReversibleResponseMessage]()

  def registerResponse(message: ResponseMessage) = {
    message match {
      case rrm: ReversibleResponseMessage =>
        historyQueue.push(rrm)
        undoneQueue.clear()
      case _ => // no-op
    }
    println(s"Undo [registerResponse]: [${historyQueue.size} / ${undoneQueue.size}], [$undoCount / $redoCount]")
  }

  def undo(gameState: GameState) = {
    undoCount += 1
    val msg = historyQueue.pop()
    val reverse = getReverse(msg, gameState)
    undoneQueue.push(reverse)
    reverse
  }

  def redo(gameState: GameState) = {
    redoCount += 1
    val msg = undoneQueue.pop()
    val reverse = getReverse(msg, gameState)
    historyQueue.push(reverse)
    reverse
  }

  private[this] def getReverse(rrm: ReversibleResponseMessage, gameState: GameState): ReversibleResponseMessage = rrm match {
    case cr: CardRevealed =>
      cr.card.u = false
      gameState.hideCardFromAll(cr.card).headOption.getOrElse(throw new IllegalStateException("No hide response."))
    case ch: CardHidden =>
      val card = gameState.getCard(ch.id)
      card.u = true
      gameState.revealCardToAll(card).headOption.getOrElse(throw new IllegalStateException("No reveal response."))
    case cm: CardMoved =>
      val src = gameState.pilesById(cm.source)
      val tgt = gameState.pilesById(cm.target)
      tgt.cards.find(_.id == cm.card) match {
        case Some(card) =>
          tgt.removeCard(card)
          src.addCard(card)
          CardMoved(cm.card, source = cm.target, target = cm.source, turn = cm.turn.map(!_))
        case None => throw new IllegalStateException(s"Can't find card [${cm.card}].")
      }
    case cm: CardsMoved =>
      val src = gameState.pilesById(cm.source)
      val tgt = gameState.pilesById(cm.target)
      val cards = cm.cards.map(x => tgt.cards.find(_.id == x).getOrElse(throw new IllegalStateException(s"Can't find card [$x].")))
      cards.foreach { card =>
        tgt.removeCard(card)
        src.addCard(card)
      }
      CardsMoved(cm.cards, source = cm.target, target = cm.source, turn = cm.turn.map(!_))
    case ms: MessageSet =>
      MessageSet(ms.messages.reverse.flatMap {
        case rrm: ReversibleResponseMessage => Some(getReverse(rrm, gameState))
        case _ => None
      })
  }
}
