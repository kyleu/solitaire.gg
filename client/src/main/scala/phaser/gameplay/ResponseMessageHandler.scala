package phaser.gameplay

import models._
import models.game.UndoHelper
import phaser.PhaserGame

class ResponseMessageHandler(g: PhaserGame, undo: UndoHelper, debug: Boolean) {
  def handle(msg: ResponseMessage, registerUndo: Boolean = false): Unit = {
    if (debug) { utils.Logging.info(s"Received response message (undo: $registerUndo) - [$msg].") }

    if (registerUndo) {
      undo.registerResponse(msg)
    }
    val p = g.getPlaymat

    g.gameplay.services.state.apply(msg)

    msg match {
      case ms: MessageSet => ms.messages.foreach(m => handle(m))
      case gj: GameJoined => throw new IllegalStateException("Received unexpected GameJoined message.")
      case pm: PossibleMoves => g.possibleMoves = pm.moves
      case cr: CardRevealed => cardRevealed(cr)
      case ch: CardHidden => p.getCardSprite(ch.id).turnFaceDown()
      case cm: CardMoved => moveCard(cm.card, cm.source, cm.target, cm.turn)
      case cm: CardsMoved => cm.cards.foreach { movedCard => moveCard(movedCard, cm.source, cm.target, cm.turn) }
      case cmc: CardMoveCancelled => cmc.cards.foreach(c => p.getCardSprite(c).cancelDrag())
      case gl: GameLost => p.lose()
      case gw: GameWon => p.win(gw)
      case _ => throw new IllegalStateException(s"Unhandled response message [$msg].")
    }
  }

  private[this] def moveCard(card: Int, src: String, tgt: String, turn: Option[Boolean]) = {
    val p = g.getPlaymat
    val movedCard = p.getCardSprite(card)
    val source = p.getPileGroup(src)
    val target = p.getPileGroup(tgt)

    turn match {
      case Some(true) if !movedCard.isFaceUp => movedCard.turnFaceUp()
      case Some(false) if movedCard.isFaceUp => movedCard.turnFaceDown()
      case _ => // noop
    }

    movedCard.bringToTop()
    source.removeCard(movedCard)
    target.addCard(movedCard, None)
  }

  private[this] def cardRevealed(cr: CardRevealed) = {
    val existing = g.getPlaymat.getCardSprite(cr.card.id)
    existing.reveal(cr.card.r, cr.card.s, cr.card.u)
  }
}
