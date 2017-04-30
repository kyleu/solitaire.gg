package phaser.gameplay

import models._
import phaser.PhaserGame

class ResponseMessageHandler(g: PhaserGame) {
  def handle(msg: ResponseMessage, registerUndo: Boolean = false): Unit = msg match {
    case ms: MessageSet => ms.messages.foreach(m => handle(m))
    case gj: GameJoined => throw new IllegalStateException("Invalid GameJoined message.")
    case pm: PossibleMoves => g.possibleMoves = pm.moves
    case cr: CardRevealed => cardRevealed(cr)
    case ch: CardHidden => g.getPlaymat.getCardSprite(ch.id).turnFaceDown();
    case cm: CardMoved => moveCard(cm.card, cm.source, cm.target, cm.turn.getOrElse(false))
    case cm: CardsMoved => cm.cards.foreach { movedCard =>
      moveCard(movedCard, cm.source, cm.target, cm.turn.getOrElse(false))
    }
    case cmc: CardMoveCancelled => cmc.cards.foreach { cancelledCard =>
      g.getPlaymat.getCardSprite(cancelledCard).cancelDrag()
    }
    case gl: GameLost => g.getPlaymat.lose()
    case gw: GameWon => g.getPlaymat.win(gw)
    case _ => throw new IllegalStateException(s"Unhandled response message [$msg].")
  }

  private[this] def moveCard(card: Int, src: String, tgt: String, turn: Boolean) = {
    val movedCard = g.getPlaymat.getCardSprite(card)
    val source = g.getPlaymat.getPileGroup(src)
    val target = g.getPlaymat.getPileGroup(tgt)

    if (turn && !movedCard.faceUp) {
      movedCard.turnFaceUp()
    }
    if (turn && movedCard.faceUp) {
      movedCard.turnFaceDown()
    }

    movedCard.bringToTop()
    source.removeCard(movedCard)
    target.addCard(movedCard, None)
  }

  private[this] def cardRevealed(cr: CardRevealed) = {
    val existing = g.getPlaymat.getCardSprite(cr.card.id)
    var wasFaceUp = existing.faceUp
    existing.rank = cr.card.r
    existing.suit = cr.card.s
    if (cr.card.u && (!wasFaceUp)) {
      existing.turnFaceUp()
    } else {
      utils.Logging.warn(s"Reveal received for already revealed card [${cr.card}].")
    }
  }
}
