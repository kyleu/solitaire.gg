package models.game.pile

import models.game.pile.actions.SelectCardActions
import models.CardRevealed
import models.game.{GameState, Card, Rank}

class Tableau(id: String, cardsShown: Option[Int] = None, direction: Option[String] = Some("d")) extends Pile(id, "tableau", cardsShown = cardsShown, direction = direction) {
  override def canSelectCard(card: Card) = {
    Some(card) == this.cards.lastOption
  }

  override def canDragFrom(cards: Seq[Card]) = {
    if(cards.exists(!_.u)) {
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
  }

  override def canDragTo(cards: Seq[Card]) = {
    if(this.cards.isEmpty) {
      cards.head.r ==  Rank.King
    } else {
      val topCard = this.cards.last
      val firstDraggedCard = cards.head
      if(!topCard.u) {
        false
      } else {
        options.get("drag-to-constraint") match {
          case None =>
            if(topCard.s.color == firstDraggedCard.s.color) {
              false
            } else if(topCard.r == Rank.Ace || firstDraggedCard.r == Rank.King) {
              false
            } else {
              topCard.r.value == firstDraggedCard.r.value + 1
            }
          case Some(c) if c == "alternating-rank" =>
            topCard.r.value == firstDraggedCard.r.value + 1 || topCard.r.value == firstDraggedCard.r.value - 1
          case Some(c) =>
            log.warn("Invalid drag-to-constraint [" + c + "].")
            false
        }
      }
    }
  }

  override def onSelectCard(card: Card, gameState: GameState) = {
    if(!card.u) {
      card.u = true
      Seq(CardRevealed(card))
    } else {
      SelectCardActions.playToFoundation(this, card, gameState)
    }
  }
}
