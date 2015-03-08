package models.game.pile.actions

import models.CardMoved
import models.game.{Rank, GameState, Card}
import models.game.pile.Pile

object SelectCardActions {
  def playToFoundation(pile: Pile, card: Card, gameState: GameState) = {
    val foundations = gameState.piles.filter(_.behavior == "foundation")
    val foundation = foundations.flatMap { f =>
      if(f.cards.isEmpty && card.r == Rank.Ace) {
        Some(f)
      } else if(f.cards.lastOption.map(_.s) == Some(card.s) && f.cards.lastOption.map(_.r) == Some(Rank.Ace) && card.r == Rank.Two) {
        Some(f)
      } else if(f.cards.lastOption.map(_.s) == Some(card.s) && f.cards.lastOption.map(_.r.value) == Some(card.r.value - 1)) {
        Some(f)
      } else {
        None
      }
    }.headOption
    foundation match {
      case Some(f) =>
        pile.removeCard(card)
        f.addCard(card)
        Seq(CardMoved(card.id, pile.id, f.id))
      case None => Nil
    }
  }
}
