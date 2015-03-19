package models.game.pile.actions

import models.game.pile.Pile
import models.game.{Card, GameState}
import models.ResponseMessage

case class DragToAction(id: String, f: (Pile, Card, GameState) => Seq[ResponseMessage])

object DragToActions {
  val none = SelectCardAction("none", (pile, card, gameState) => Nil)
}
