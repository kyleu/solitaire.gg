package models.game.pile

import models.game.pile.actions.{ DragToActions, SelectPileActions, SelectCardActions }
import models.game.{ GameState, Card }
import utils.Logging

import scala.collection.mutable.ArrayBuffer

object Pile {
  val options = PileOptions()
}

case class Pile(id: String, behavior: String, options: PileOptions, cards: collection.mutable.ArrayBuffer[Card] = ArrayBuffer.empty[Card]) extends Logging {
  def addCards(cs: Seq[Card]) = cs.foreach(addCard)

  def addCard(c: Card) = {
    log.info("Adding card [" + c + "] to pile [" + id + "].")
    cards += c
    cards.size - 1
  }

  def removeCard(card: Card) = {
    val srcIdx = cards.indexOf(card)
    srcIdx match {
      case -1 => throw new IllegalArgumentException("Provided card [" + card + "] is not included in pile [" + id + "].")
      case _ => cards -= card
    }
    srcIdx
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
