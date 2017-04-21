package client.game

import java.util.UUID

import models._

trait MoveHelper extends PossibleMoveHelper with StartGameHelper {
  private[this] val requests = collection.mutable.ArrayBuffer.empty[Seq[String]]
  protected def registerRequest(stack: String*) = requests += stack
  protected def clearRequests() = requests.clear()
  override protected def getRequests = requests

  protected def send(rm: ResponseMessage, registerUndoResponse: Boolean): Unit

  protected def send(rms: Seq[ResponseMessage], registerUndoResponse: Boolean): Unit = if (rms.size == 1) {
    val msg = rms.headOption.getOrElse(throw new IllegalStateException())
    send(msg, registerUndoResponse)
  } else {
    send(MessageSet(rms), registerUndoResponse)
  }

  protected def getResult: GameResult

  protected[this] def handleSelectCard(userId: UUID, cardIdx: Int, pileId: String, auto: Boolean) = {
    val card = gs.cardsByIdx(cardIdx)
    val pile = gs.pilesById(pileId)
    if (!pile.cards.contains(card)) {
      throw new IllegalStateException(s"SelectCard for game [$gameId]: Card [${card.toString}] is not part of the [$pileId] pile.")
    }
    if (pile.canSelectCard(card, gs)) {
      val args = Seq("select-card", pileId, cardIdx.toString) ++ (if (auto) { Seq("true") } else { Seq.empty })
      registerRequest(args: _*)
      val messages = pile.onSelectCard(card, gs)
      send(messages, registerUndoResponse = true)
      if (messages.size != 1 || messages.headOption.forall {
        case _: CardRevealed => false
        case _ => true
      }) {
        registerMove()
      }
    }
  }

  protected[this] def handleSelectPile(userId: UUID, pileId: String, auto: Boolean) = {
    val pile = gs.pilesById(pileId)
    if (pile.cards.nonEmpty) {
      throw new IllegalStateException(s"SelectPile [$pileId] called on a non-empty deck.")
    }
    if (pile.canSelectPile(gs)) {
      val args = Seq("select-pile", pileId) ++ (if (auto) { Seq("true") } else { Seq.empty })
      registerRequest(args: _*)
      val messages = pile.onSelectPile(gs)
      send(messages, registerUndoResponse = true)
      registerMove()
    }
  }

  protected[this] def handleMoveCards(userId: UUID, cardIds: Seq[Int], source: String, target: String, auto: Boolean) = {
    val cards = cardIds.map(gs.cardsByIdx)
    val sourcePile = gs.pilesById(source)
    val targetPile = gs.pilesById(target)
    for (c <- cards) {
      if (!sourcePile.cards.contains(c)) {
        throw new IllegalArgumentException(s"Card [$c] is not a part of source pile [${sourcePile.id}].")
      }
    }
    if (sourcePile.canDragFrom(cards, gs)) {
      if (targetPile.canDragTo(sourcePile, cards, gs)) {
        val args = Seq("move-cards", source, target) ++ cardIds.map(_.toString) ++ (if (auto) { Seq("true") } else { Seq.empty })
        registerRequest(args: _*)
        val messages = targetPile.onDragTo(sourcePile, cards, gs)
        send(messages, registerUndoResponse = true)
        registerMove()
      } else {
        send(CardMoveCancelled(cardIds, source))
      }
    } else {
      send(CardMoveCancelled(cardIds, source))
    }
  }
}
