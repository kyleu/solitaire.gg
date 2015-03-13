package models.game.pile

import models.ResponseMessage
import models.game.pile.constraints.{DragToConstraints, DragFromConstraints, SelectPileConstraints, SelectCardConstraints}
import models.game.{GameState, Card}
import utils.Logging

import scala.collection.mutable.ArrayBuffer

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

  final def canSelectCard(card: Card) = options.selectCardConstraint.getOrElse(SelectCardConstraints.never).f(this, card)
  final def canSelectPile = options.selectPileConstraint.getOrElse(SelectPileConstraints.never).f(this)

  final def canDragFrom(cards: Seq[Card]) = options.dragFromConstraint.getOrElse(DragFromConstraints.never).f(this, cards)
  final def canDragTo(cards: Seq[Card]) = options.dragToConstraint.getOrElse(DragToConstraints.never).f(this, cards)

  def onSelectCard(card: Card, gameState: GameState): Seq[ResponseMessage] = {
    log.debug("Card [" + card + "] selected with no action.")
    Nil
  }
  def onSelectPile(gameState: GameState): Seq[ResponseMessage] = {
    log.debug("Pile [" + this + "] selected with no action.")
    Nil
  }

  override def toString: String = id + ": " + cards.map(_.toString).mkString(", ")
}
