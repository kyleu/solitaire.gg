package models.game.pile

import models.ResponseMessage
import models.game.{GameState, Card}
import utils.Logging

import scala.collection.mutable.ArrayBuffer

case class Pile(id: String, behavior: String, cards: collection.mutable.ArrayBuffer[Card] = ArrayBuffer.empty[Card]) extends Logging {
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

  def canDragFrom(cards: Seq[Card]) = false
  def canDragTo(cards: Seq[Card]) = false
  def canSelectCard(card: Card) = false
  def canSelectPile = false

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
