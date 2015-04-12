package models.game.pile.actions

import models.{ CardMoved, ResponseMessage }
import models.game.GameState
import models.game.pile.Pile

case class SelectPileAction(id: String, f: (Pile, GameState) => Seq[ResponseMessage])

object SelectPileActions {
  val none = SelectPileAction("none", (pile, gameState) => Nil)

  def moveAll(target: String) = SelectPileAction("move-all", (pile, gameState) => {
    val targetPile = gameState.pilesById(target)
    targetPile.cards.map { card =>
      targetPile.removeCard(card)
      val tgtIdx = pile.addCard(card)
      CardMoved(card.id, target, pile.id, tgtIdx, turnFaceDown = true)
    }
  })
}
