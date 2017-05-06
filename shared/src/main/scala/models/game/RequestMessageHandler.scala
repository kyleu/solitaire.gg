package models.game

import java.util.UUID

import models._

class RequestMessageHandler(gs: GameState, send: (ResponseMessage, Boolean) => Unit, registerMove: () => Unit) {
  private[this] def sendSeq(rms: Seq[ResponseMessage], registerUndoResponse: Boolean): Unit = if (rms.size == 1) {
    val msg = rms.headOption.getOrElse(throw new IllegalStateException())
    send(msg, registerUndoResponse)
  } else {
    send(MessageSet(rms), registerUndoResponse)
  }

  def handle(player: UUID, msg: RequestMessage) = msg match {
    case GetVersion => send(VersionResponse("0.0"), false)
    case p: Ping => send(Pong(p.timestamp), false)
    case sc: SelectCard => handleSelectCard(player, sc.card, sc.pile, sc.auto)
    case sp: SelectPile => handleSelectPile(player, sp.pile, sp.auto)
    case mc: MoveCards => handleMoveCards(player, mc.cards, mc.src, mc.tgt, mc.auto)
    case Undo => handleUndo()
    case Redo => handleRedo()
    case _ => throw new IllegalStateException(s"Unhandled request message [$msg].")
  }

  private[this] def handleSelectCard(userId: UUID, cardId: Int, pileId: String, auto: Boolean) = {
    val card = gs.cardsById(cardId)
    val pile = gs.pilesById(pileId)
    if (!pile.cards.contains(card)) {
      throw new IllegalStateException(s"SelectCard for game [${gs.gameId}]: Card [${card.toString}] is not part of the [$pileId] pile.")
    }
    if (pile.canSelectCard(card, gs)) {
      val messages = pile.onSelectCard(card, gs)
      sendSeq(messages, registerUndoResponse = true)
      if (messages.size != 1 || messages.headOption.forall {
        case _: CardRevealed => false
        case _ => true
      }) {
        registerMove()
      }
    }
  }

  private[this] def handleSelectPile(userId: UUID, pileId: String, auto: Boolean) = {
    val pile = gs.pilesById(pileId)
    if (pile.cards.nonEmpty) {
      throw new IllegalStateException(s"SelectPile [$pileId] called on a non-empty deck.")
    }
    if (pile.canSelectPile(gs)) {
      val messages = pile.onSelectPile(gs)
      sendSeq(messages, registerUndoResponse = true)
      registerMove()
    }
  }

  private[this] def handleMoveCards(userId: UUID, cardIds: Seq[Int], source: String, target: String, auto: Boolean) = {
    val cards = cardIds.map(gs.cardsById)
    val sourcePile = gs.pilesById(source)
    val targetPile = gs.pilesById(target)
    for (c <- cards) {
      if (!sourcePile.cards.contains(c)) {
        throw new IllegalArgumentException(s"Card [$c] is not a part of source pile [${sourcePile.id}].")
      }
    }
    if (sourcePile.canDragFrom(cards, gs)) {
      if (targetPile.canDragTo(sourcePile, cards, gs)) {
        val messages = targetPile.onDragTo(sourcePile, cards, gs)
        sendSeq(messages, registerUndoResponse = true)
        registerMove()
      } else {
        send(CardMoveCancelled(cardIds, source), false)
      }
    } else {
      send(CardMoveCancelled(cardIds, source), false)
    }
  }

  def handleUndo() = {
    println("UNDO!")
  }

  def handleRedo() = {
    println("REDO!")
  }
}
