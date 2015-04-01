package models.game.pile

import models.ResponseMessage
import models.game.pile.actions.{ DragToActions, SelectPileActions, SelectCardActions }
import models.game.{ GameState, Card }
import utils.Logging

import scala.collection.mutable.ArrayBuffer

object Pile {
  val options = PileOptions()
}

case class Pile(id: String, behavior: String, options: PileOptions, cards: collection.mutable.ArrayBuffer[Card] = ArrayBuffer.empty[Card]) extends Logging {
  def addCards(cs: Seq[Card]) = cs.foreach(addCard)

  def addCard(c: Card) {
    cards += c
  }

  def removeCard(card: Card) {
    cards.find(_.id == card.id) match {
      case Some(_) => cards -= card
      case None => throw new IllegalArgumentException("Provided card [" + card + "] is not included in pile [" + id + "].")
    }
  }

  def canSelectCard(card: Card, gameState: GameState) = options.selectCardConstraint.f(this, Seq(card), gameState)
  def canSelectPile(gameState: GameState) = options.selectPileConstraint.f(this, Nil, gameState)

  def canDragFrom(cards: Seq[Card], gameState: GameState) = options.dragFromConstraint.f(this, cards, gameState)
  def canDragTo(cards: Seq[Card], gameState: GameState) = options.dragToConstraint.f(this, cards, gameState)

  def onSelectCard(card: Card, gameState: GameState): Seq[ResponseMessage] = options.selectCardAction.getOrElse(SelectCardActions.none).f(this, card, gameState)
  def onSelectPile(gameState: GameState): Seq[ResponseMessage] = options.selectPileAction.getOrElse(SelectPileActions.none).f(this, gameState)
  def onDragTo(src: Pile, cards: Seq[Card], gameState: GameState) = options.dragToAction.getOrElse(DragToActions.moveCards).f(src, cards, this, gameState)

  override def toString: String = id + ": " + cards.map(_.toString).mkString(", ")
}
