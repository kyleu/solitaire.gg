package models.game.pile

import models.ResponseMessage
import models.game.pile.actions.{SelectPileActions, SelectCardActions}
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

  final def canSelectCard(card: Card, gameState: GameState) = options.selectCardConstraint.getOrElse(SelectCardConstraints.never).f(this, card, gameState)
  final def canSelectPile(gameState: GameState) = options.selectPileConstraint.getOrElse(SelectPileConstraints.never).f(this, gameState)

  final def canDragFrom(cards: Seq[Card], gameState: GameState) = options.dragFromConstraint.getOrElse(DragFromConstraints.never).f(this, cards, gameState)
  final def canDragTo(cards: Seq[Card], gameState: GameState) = options.dragToConstraint.getOrElse(DragToConstraints.never).f(this, cards, gameState)

  def onSelectCard(card: Card, gameState: GameState): Seq[ResponseMessage] = options.selectCardAction.getOrElse(SelectCardActions.none).f(this, card, gameState)
  def onSelectPile(gameState: GameState): Seq[ResponseMessage] = options.selectPileAction.getOrElse(SelectPileActions.none).f(this, gameState)

  override def toString: String = id + ": " + cards.map(_.toString).mkString(", ")
}
