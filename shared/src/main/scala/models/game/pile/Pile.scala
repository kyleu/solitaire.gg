package models.game.pile

import models.game.pile.actions.{ DragToActions, SelectPileActions, SelectCardActions }
import models.game.{ GameState, Card }

import scala.collection.mutable.ArrayBuffer

object Pile {
  val options = PileOptions()
}

case class Pile(id: String, behavior: String, options: PileOptions, cards: collection.mutable.ArrayBuffer[Card] = ArrayBuffer.empty[Card]) {
  def addCards(cs: Seq[Card]) = cs.foreach(addCard)

  def addCard(c: Card) {
    cards += c
  }

  def removeCard(card: Card) {
    if (!cards.contains(card)) {
      throw new IllegalArgumentException("Provided card [" + card + "] is not included in pile [" + id + "].")
    }
    cards -= card
  }

  def canSelectCard(card: Card, gameState: GameState) = options.selectCardConstraint.exists(_.f(this, Seq(card), gameState))
  def canSelectPile(gameState: GameState) = options.selectPileConstraint.exists(_.f(this, Nil, gameState))

  def canDragFrom(cards: Seq[Card], gameState: GameState) = options.dragFromConstraint.exists(_.f(this, cards, gameState))
  def canDragTo(cards: Seq[Card], gameState: GameState) = options.dragToConstraint.exists(_.f(this, cards, gameState))

  def onSelectCard(card: Card, gameState: GameState) = options.selectCardAction.getOrElse(SelectCardActions.none).f(this, card, gameState)
  def onSelectPile(gameState: GameState) = options.selectPileAction.getOrElse(SelectPileActions.none).f(this, gameState)
  def onDragTo(src: Pile, cards: Seq[Card], gameState: GameState) = options.dragToAction.getOrElse(DragToActions.moveCards).f(src, cards, this, gameState)

  override def toString: String = id + ": " + cards.map(_.toString).mkString(", ")
}
