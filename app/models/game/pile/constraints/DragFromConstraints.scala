package models.game.pile.constraints

import models.game.{Rank, Card}
import models.game.pile.Pile

object DragFromConstraints {
  def never(pile: Pile, cards: Seq[Card]) = false
  def isTopCard(pile: Pile, cards: Seq[Card]) = cards.headOption == pile.cards.lastOption

  def klondike(pile: Pile, cards: Seq[Card]) = if(cards.exists(!_.u)) {
    false
  } else {
    var valid = true
    var lastCard: Option[Card] = None

    for(c <- cards) {
      if(lastCard.isDefined) {
        if(c.s.color == lastCard.get.s.color) {
          valid = false
        }
        if(c.r == Rank.Ace || c.r.value != (lastCard.get.r.value - 1)) {
          valid = false
        }
      }
      lastCard = Some(c)
    }
    valid
  }


  def apply(key: Option[String]) = key match {
    case Some(k) => k match {
      case "top-card-only" => isTopCard _
      case "klondike" => klondike _
      case _ => throw new IllegalArgumentException("Invalid select card constraint [" + k + "].")
    }
    case None => never _
  }
}
