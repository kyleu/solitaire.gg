package models.game

import models._

object GameStateApply {
  def applyMessage(gs: GameState, msg: ResponseMessage) = msg match {
    case cr: CardRevealed => cardRevealed(gs, cr)
    case ch: CardHidden => gs.getCard(ch.id).u = false
    case cm: CardMoved => moveCard(gs, cm.card, cm.source, cm.target, cm.turn)
    case cm: CardsMoved => cm.cards.foreach { movedCard => moveCard(gs, movedCard, cm.source, cm.target, cm.turn) }
    case _ => // noop
  }

  private[this] def cardRevealed(gs: GameState, cr: CardRevealed) = {
    gs.revealInPlace(cr.card)
  }

  private[this] def moveCard(gs: GameState, card: Int, src: String, tgt: String, turn: Option[Boolean]) = {
    val removed = gs.pilesById(src).removeCardById(card)
    gs.pilesById(tgt).addCard(removed)
  }
}
