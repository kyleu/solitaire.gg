package models.game.pile.constraints

import models.game.Rank._
import models.game.pile.Pile
import models.game.{ Rank, Card, GameState }

case class Constraint(id: String, f: (Pile, Seq[Card], GameState) => Boolean, clientOptions: Option[Map[String, String]] = None)

object Constraints {
  val never = Constraint("never", (pile, cards, gameState) => false)
  val empty = Constraint("empty", (pile, cards, gameState) => pile.cards.isEmpty)
  val notEmpty = Constraint("empty", (pile, cards, gameState) => pile.cards.nonEmpty)

  def allOf(id: String, constraints: Constraint*) = Constraint(id, (pile, cards, gameState) => {
    val results = constraints.map(_.f(pile, cards, gameState))
    !results.contains(false)
  })

  def specificRank(r: Rank) = Constraint("rank-" + r, (pile, cards, gameState) => {
    cards.tail.isEmpty && cards.headOption.getOrElse(throw new IllegalStateException()).r == r
  })

  val topCardOnly = Constraint("top-card-only", (pile, cards, gameState) => {
    pile.cards.lastOption == cards.headOption
  })

  val sameRank = Constraint("same-rank", (pile, cards, gameState) => {
    val topCardRank = pile.cards.lastOption.map(_.r).getOrElse(Rank.Unknown)
    val firstDraggedCardRank = cards.headOption.getOrElse(throw new IllegalStateException()).r
    topCardRank == firstDraggedCardRank
  })

  val lowerRank = Constraint("lower-rank", (pile, cards, gameState) => {
    val firstDraggedCardRank = cards.headOption.getOrElse(throw new IllegalStateException()).r
    pile.cards.lastOption match {
      case Some(c) => c.r match {
        case Two => firstDraggedCardRank == Ace
        case _ => c.r.value == firstDraggedCardRank.value + 1
      }
      case None => false
    }
  })

  val alternatingRank = Constraint("alternating-rank", (pile, cards, gameState) => {
    val topCardRank = pile.cards.last.r
    val firstDraggedCardRank = cards.headOption.getOrElse(throw new IllegalStateException()).r
    topCardRank match {
      case King => firstDraggedCardRank == Queen
      case Ace => firstDraggedCardRank == Two
      case Two => firstDraggedCardRank == Ace || firstDraggedCardRank == Three
      case _ => topCardRank.value == firstDraggedCardRank.value + 1 || topCardRank.value == firstDraggedCardRank.value - 1
    }
  })

  def total(target: Int, aceHigh: Boolean = true) = Constraint("total-" + target, (pile, cards, gameState) => {
    if (pile.cards.isEmpty) {
      false
    } else {
      val topCardRank = pile.cards.last.r
      val topCardRankValue = if (topCardRank == Ace && !aceHigh) { 1 } else { topCardRank.value }
      val firstDraggedCardRank = cards.headOption.getOrElse(throw new IllegalStateException()).r
      val firstDraggedCardRankValue = if (firstDraggedCardRank == Ace && !aceHigh) { 1 } else { firstDraggedCardRank.value }
      val totalValue = topCardRankValue + firstDraggedCardRankValue
      totalValue == target
    }
  })

  val descendingSequenceSameSuit = Constraint("descending", (pile, cards, gameState) => {
    var priorCard: Option[Card] = None
    var valid = true
    for (card <- cards) {
      priorCard.foreach { pc =>
        if (pc.s != card.s) {
          valid = false
        }
        if (pc.r.value != card.r.value - 1) {
          valid = false
        }
      }
      priorCard = Some(card)
    }
    valid
  })

  val klondikeDragFrom = Constraint("klondike", KlondikeConstraintLogic.dragFrom)
  val klondikeFoundationDragTo = Constraint("foundation", KlondikeConstraintLogic.foundationDragTo)
  val klondikeTableauDragTo = Constraint("klondike", KlondikeConstraintLogic.tableauDragTo)
  val klondikeSelectCard = Constraint("klondike", KlondikeConstraintLogic.selectCard)

  val alternatingRankToFoundation = Constraint("alternating-rank", (pile, cards, gameState) => {
    if (cards.tail.nonEmpty || !topCardOnly.f(pile, cards, gameState)) {
      false
    } else {
      val foundation = gameState.pilesById("foundation")
      val topCardRank = foundation.cards.last.r
      val firstCard = cards.headOption.getOrElse(throw new IllegalStateException())
      topCardRank match {
        case King => firstCard.r == Queen
        case Ace => firstCard.r == Two
        case Two => firstCard.r == Ace || firstCard.r == Three
        case _ => topCardRank.value == firstCard.r.value + 1 || topCardRank.value == firstCard.r.value - 1
      }
    }
  })

  def pilesEmpty(piles: String*) = Constraint("piles-empty", (pile, cards, gameState) => {
    var ret = true
    for (p <- piles) {
      if (gameState.pilesById(p).cards.nonEmpty) {
        ret = false
      }
    }
    ret
  }, Some(Map("piles" -> piles.mkString(","))))
}
