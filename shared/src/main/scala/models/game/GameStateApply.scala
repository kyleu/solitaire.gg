package models.game

import models._

object GameStateApply {
  def applyMessage(gs: GameState, msg: ResponseMessage): Unit = msg match {
    case cr: CardRevealed => cardRevealed(gs, cr)
    case ch: CardHidden => gs.getCard(ch.id).u = false
    case cm: CardsMoved => cm.cards.foreach { movedCard => moveCard(gs, movedCard, cm.source, cm.target, cm.turn) }
    case _ => // noop
  }

  private[this] def cardRevealed(gs: GameState, cr: CardRevealed) = {
    val p = gs.pileSets.flatMap(_.piles).find(_.cards.exists(_.id == cr.card.id)).getOrElse(throw new IllegalStateException(s"No pile with card [${cr.card}]."))
    p.cards(p.cards.indexWhere(_.id == cr.card.id)) = cr.card
    gs.players.map(_.userId).exists(player => gs.revealCardToPlayer(cr.card, player))
  }

  private[this] def moveCard(gs: GameState, card: Int, src: String, tgt: String, turn: Option[Boolean]) = {
    val removed = gs.pilesById(src).removeCardById(card)
    turn.foreach(gs.cardsById(card).u = _)
    gs.pilesById(tgt).addCard(removed)
  }
}
