package models.game.pile.constraints

import models.game.Rank._
import models.game.pile.Pile
import models.game.{ Rank, Card, GameState }

case class Constraint(id: String, f: (Pile, Seq[Card], GameState) => Boolean, clientOptions: Option[Map[String, String]] = None)

object Constraints {
  val always = Constraint("always", (pile, cards, gameState) => true)
  val never = Constraint("never", (pile, cards, gameState) => false)
  val empty = Constraint("empty", (pile, cards, gameState) => pile.cards.isEmpty)
  val notEmpty = Constraint("empty", (pile, cards, gameState) => pile.cards.nonEmpty)

  def allOf(id: String, constraints: Constraint*) = Constraint(id, (pile, cards, gameState) => {
    val results = constraints.map(_.f(pile, cards, gameState))
    !results.contains(false)
  })

  val faceUp = Constraint("face-up", (pile, cards, gameState) => {
    !cards.exists(!_.u)
  })

  val topCardOnly = Constraint("top-card-only", (pile, cards, gameState) => {
    pile.cards.lastOption == cards.headOption
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

  def pilesEmpty(piles: String*) = Constraint("piles-empty", (pile, cards, gameState) => {
    !piles.exists(p => gameState.pilesById(p).cards.nonEmpty)
  }, Some(Map("piles" -> piles.mkString(","))))

  def pilesNonEmpty(piles: String*) = Constraint("piles-non-empty", (pile, cards, gameState) => {
    !piles.exists(p => gameState.pilesById(p).cards.isEmpty)
  }, Some(Map("piles" -> piles.mkString(","))))

  def specificRank(r: Rank) = RankConstraintLogic.specificRank(r)
  val sameRank = RankConstraintLogic.sameRank
  val lowerRank = RankConstraintLogic.lowerRank
  val alternatingRank = RankConstraintLogic.alternatingRank
  val alternatingRankToFoundation = RankConstraintLogic.alternatingRankToFoundation

  val klondikeDragFrom = Constraint("klondike", KlondikeConstraintLogic.dragFrom)
  val klondikeFoundationDragTo = Constraint("foundation", KlondikeConstraintLogic.foundationDragTo)
  val klondikeTableauDragTo = Constraint("klondike", KlondikeConstraintLogic.tableauDragTo)
  val klondikeSelectCard = Constraint("klondike", KlondikeConstraintLogic.selectCard)
}
