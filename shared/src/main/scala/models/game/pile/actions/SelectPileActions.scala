package models.game.pile.actions

import models.{ CardMoved, ResponseMessage }
import models.game.GameState
import models.game.pile.Pile

case class SelectPileAction(id: String, f: (Pile, GameState) => Seq[ResponseMessage])

object SelectPileActions {
  val none = SelectPileAction("none", (pile, gameState) => Nil)

  def moveAllFrom(targets: Seq[String]) = SelectPileAction("move-all", (pile, gameState) => {
    targets.flatMap { target =>
      val targetPile = gameState.pilesById(target)
      val cards = Seq(targetPile.cards.reverse: _*)
      cards.map { card =>
        targetPile.removeCard(card)
        pile.addCard(card)
        CardMoved(card.id, target, pile.id, turn = Some(false))
      }
    }
  })

  def moveAllNonEmpty(target: String) = SelectPileAction("move-all", (pile, gameState) => {
    val targetPile = gameState.pilesById(target)
    val cards = Seq(targetPile.cards.reverse: _*)
    cards.map { card =>
      targetPile.removeCard(card)
      pile.addCard(card)
      CardMoved(card.id, target, pile.id, turn = Some(false))
    }
  })
}
