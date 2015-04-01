package models.game.pile.constraints

import models.game.pile.Pile
import models.game.{ GameState, Rank, Card }

object KlondikeConstraintLogic {
  val dragFrom = (pile: Pile, cards: Seq[Card], gameState: GameState) => if (cards.exists(!_.u)) {
    false
  } else {
    var valid = true
    var lastCard: Option[Card] = None

    for (c <- cards) {
      if (lastCard.isDefined) {
        if (c.s.color == lastCard.get.s.color) { valid = false }
        if (c.r == Rank.Ace || c.r.value != (lastCard.get.r.value - 1)) { valid = false }
      }
      lastCard = Some(c)
    }
    valid
  }

  val foundationDragTo = (pile: Pile, cards: Seq[Card], gameState: GameState) => if (cards.length == 1) {
    if (pile.cards.isEmpty) {
      cards.head.r == Rank.Ace
    } else if (pile.cards.last.s == cards.head.s && pile.cards.last.r == Rank.Ace && cards.head.r == Rank.Two) {
      true
    } else if (pile.cards.last.s == cards.head.s && pile.cards.last.r.value + 1 == cards.head.r.value) {
      true
    } else {
      false
    }
  } else {
    false
  }

  val tableauDragTo = (pile: Pile, cards: Seq[Card], gameState: GameState) => if (pile.cards.isEmpty) {
    cards.head.r == Rank.King
  } else {
    val topCard = pile.cards.last
    val firstDraggedCard = cards.head
    if (!topCard.u) {
      false
    } else {
      if (topCard.s.color == firstDraggedCard.s.color) {
        false
      } else if (topCard.r == Rank.Ace || firstDraggedCard.r == Rank.King) {
        false
      } else {
        topCard.r.value == firstDraggedCard.r.value + 1
      }
    }
  }

  val selectCard = (pile: Pile, cards: Seq[Card], gameState: GameState) => {
    if (pile.cards.lastOption != Some(cards.head)) {
      false
    } else if (!cards.head.u) {
      true
    } else {
      val foundations = gameState.piles.filter(_.behavior == "foundation")
      val foundation = foundations.flatMap { f =>
        if (f.cards.isEmpty && cards.head.r == Rank.Ace) {
          Some(f)
        } else if (f.cards.lastOption.map(_.s) == Some(cards.head.s) && f.cards.lastOption.map(_.r) == Some(Rank.Ace) && cards.head.r == Rank.Two) {
          Some(f)
        } else if (f.cards.lastOption.map(_.s) == Some(cards.head.s) && f.cards.lastOption.map(_.r.value) == Some(cards.head.r.value - 1)) {
          Some(f)
        } else {
          None
        }
      }.headOption
      foundation match {
        case Some(f) => true
        case None => false
      }
    }
  }
}
