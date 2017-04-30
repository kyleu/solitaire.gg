package phaser.gameplay

import models._
import phaser.PhaserGame

class ResponseMessageHandler(g: PhaserGame) {
  private[this] def moveCard(card: Int, src: String, tgt: String, turn: Option[Boolean]) = {
    val movedCard = g.getPlaymat.getCardSprite(card)
    val source = g.getPlaymat.getPileGroup(src)
    val target = g.getPlaymat.getPileGroup(tgt)

    if (turn.contains(true) && !movedCard.card.u) {
      movedCard.turnFaceUp()
    }
    if (turn.forall(x => !x) && movedCard.card.u) {
      movedCard.turnFaceDown()
    }

    movedCard.bringToTop()
    source.removeCard(movedCard)
    target.addCard(movedCard, None)
  }

  def handle(msg: ResponseMessage, registerUndo: Boolean): Unit = msg match {
    case ms: MessageSet => ms.messages.foreach(m => handle(m, registerUndo = false))
    case gj: GameJoined => throw new IllegalStateException("Invalid GameJoined message.")
    case pm: PossibleMoves => g.possibleMoves = pm.moves
    case cr: CardRevealed =>
      val existing = g.getPlaymat.getCardSprite(cr.card.id)
      // TODO
      // existing.rank = cr.card.r
      // existing.suit = cr.card.s
      if (cr.card.u && (!existing.card.u)) {
        existing.turnFaceUp()
      } else {
        utils.Logging.warn(s"Reveal received for already revealed card [${cr.card}].")
      }
    case ch: CardHidden =>
      val hidden = g.getPlaymat.getCardSprite(ch.id)
      hidden.turnFaceDown();
    case cm: CardMoved =>
      moveCard(cm.card, cm.source, cm.target, cm.turn)
    case cm: CardsMoved => cm.cards.foreach { movedCard =>
      moveCard(movedCard, cm.source, cm.target, cm.turn)
    }
    case cmc: CardMoveCancelled => cmc.cards.foreach { cancelledCard =>
      g.getPlaymat.getCardSprite(cancelledCard).cancelDrag()
    }
    case gl: GameLost =>
      g.getPlaymat.lose()
    case gw: GameWon =>
      g.getPlaymat.win(gw)

    case _ => throw new IllegalStateException(s"Unhandled response message [$msg].")
  }
}
