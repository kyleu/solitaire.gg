package models.game.pile

import models.game.pile.actions.{ DragToActions, SelectPileActions, SelectCardActions }
import models.game.pile.options.PileOptions
import models.game.rules.{ SuitMatchRule, RankMatchRule }
import models.game.{ Rank, GameState, Card }

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

object Pile {
  val options = PileOptions()
}

case class Pile(id: String, options: PileOptions, cards: collection.mutable.ArrayBuffer[Card] = ArrayBuffer.empty[Card]) {
  var pileSet: Option[PileSet] = None

  def addCards(cs: Seq[Card]) = cs.foreach(addCard)

  def addCard(c: Card) {
    cards += c
  }

  def removeCard(card: Card) {
    if (!cards.contains(card)) {
      throw new IllegalArgumentException(s"Provided card [$card] is not included in pile [$id].")
    }
    cards -= card
  }

  def canSelectCard(card: Card, gameState: GameState) = options.selectCardConstraint.exists(_.f(this, this, Seq(card), gameState))
  def canSelectPile(gameState: GameState) = options.selectPileConstraint.exists(_.f(this, this, Nil, gameState))

  def canDragFrom(cards: Seq[Card], gameState: GameState) = options.dragFromConstraint.exists(_.f(this, this, cards, gameState))
  def canDragTo(src: Pile, cards: Seq[Card], gameState: GameState) = options.dragToConstraint.exists(_.f(src, this, cards, gameState))

  def onSelectCard(card: Card, gameState: GameState) = options.selectCardAction.getOrElse(SelectCardActions.none).f(this, card, gameState)
  def onSelectPile(gameState: GameState) = options.selectPileAction.getOrElse(SelectPileActions.none).f(this, gameState)
  def onDragTo(src: Pile, cards: Seq[Card], gameState: GameState) = options.dragToAction.getOrElse(DragToActions.moveCards).f(src, cards, this, gameState)

  def isSorted(requireFaceUp: Boolean, rmr: RankMatchRule, smr: SuitMatchRule, lowRank: Rank, wrap: Boolean): Boolean = {
    if(requireFaceUp && this.cards.exists(!_.u)) {
      false
    } else {
      isSorted(this.cards.reverse.toList, rmr, smr, lowRank, wrap)
    }
  }

  @tailrec
  private[this] def isSorted(l: List[Card], rmr: RankMatchRule, smr: SuitMatchRule, lowRank: Rank, wrap: Boolean): Boolean = l match {
    case Nil => true
    case left :: Nil => true
    case left :: xs =>
      val right = xs.headOption.getOrElse(throw new IllegalStateException())
      val rankMatch = rmr.check(left.r, right.r, lowRank, wrap)
      val suitMatch = smr.check(left.s, right.s)
      rankMatch && suitMatch && isSorted(xs, rmr, smr, lowRank, wrap)
  }

  override def toString: String = s"$id: ${cards.map(_.toString).mkString(", ")}"
}
