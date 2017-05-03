package phaser.gameplay

import models._
import phaser.PhaserGame

class ResponseMessageHandler(g: PhaserGame, debug: Boolean) {
  def handle(msg: ResponseMessage, registerUndo: Boolean = false): Unit = {
    val p = g.getPlaymat
    if (debug) { utils.Logging.info(s"Received response message [$msg].") }
    msg match {
      case ms: MessageSet => ms.messages.foreach(m => handle(m))
      case gj: GameJoined => throw new IllegalStateException("Received unexpected GameJoined message.")
      case pm: PossibleMoves => g.possibleMoves = pm.moves
      case cr: CardRevealed => cardRevealed(cr)
      case ch: CardHidden => p.getCardSprite(ch.id).turnFaceDown();
      case cm: CardMoved => moveCard(cm.card, cm.source, cm.target, cm.turn.getOrElse(false))
      case cm: CardsMoved => cm.cards.foreach { movedCard =>
        moveCard(movedCard, cm.source, cm.target, cm.turn.getOrElse(false))
      }
      case cmc: CardMoveCancelled => cmc.cards.foreach(c => p.getCardSprite(c).cancelDrag())
      case gl: GameLost => p.lose()
      case gw: GameWon => p.win(gw)
      case _ => throw new IllegalStateException(s"Unhandled response message [$msg].")
    }
  }

  private[this] def moveCard(card: Int, src: String, tgt: String, turn: Boolean) = {
    val p = g.getPlaymat
    val movedCard = p.getCardSprite(card)
    val source = p.getPileGroup(src)
    val target = p.getPileGroup(tgt)

    if (turn && !movedCard.isFaceUp) {
      movedCard.turnFaceUp()
    }
    if (turn && movedCard.isFaceUp) {
      movedCard.turnFaceDown()
    }

    movedCard.bringToTop()
    source.removeCard(movedCard)
    target.addCard(movedCard, None)
  }

  private[this] def cardRevealed(cr: CardRevealed) = {
    utils.Logging.info(s"Card revealed: ${cr.card}")
    val existing = g.getPlaymat.getCardSprite(cr.card.id)
    //g.gameplay.services.state.revealInPlace(cr.card, DataHelper.deviceId)
    existing.reveal(cr.card.r, cr.card.s, cr.card.u)
  }
}
