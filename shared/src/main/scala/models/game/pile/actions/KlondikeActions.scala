package models.game.pile.actions

import models.game.Rank

object KlondikeActions {
  val klondike = SelectCardAction("klondike", (pile, card, gameState) => {
    if (!card.u) {
      card.u = true
      gameState.revealCardToAll(card)
    } else {
      val foundations = gameState.pileSets.filter(_.behavior == "foundation").flatMap(_.piles)
      val foundation = foundations.flatMap { f =>
        if (f.cards.isEmpty && card.r == Rank.Ace) {
          Some(f)
        } else if (f.cards.lastOption.map(_.s) == Some(card.s) && f.cards.lastOption.map(_.r) == Some(Rank.Ace) && card.r == Rank.Two) {
          Some(f)
        } else if (f.cards.lastOption.map(_.s) == Some(card.s) && f.cards.lastOption.map(_.r.value) == Some(card.r.value - 1)) {
          Some(f)
        } else {
          None
        }
      }.headOption
      foundation match {
        case Some(f) => SelectCardActions.moveCard(card, pile, f, gameState)
        case None => Nil
      }
    }
  })
}
