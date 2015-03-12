package models.game.pile

import models.game.pile.actions.SelectCardActions
import models.CardRevealed
import models.game.{GameState, Card}

object Tableau {
  private val defaultOptions = PileOptions(
    direction = Some("d"),
    selectCardConstraint = Some("top-card-only"),
    dragFromConstraint = Some("klondike"),
    dragToConstraint = Some("klondike")
  )
}

class Tableau(id: String, options: PileOptions = PileOptions.empty) extends Pile(id, "tableau", Tableau.defaultOptions.combine(options)) {
  override def onSelectCard(card: Card, gameState: GameState) = {
    if(!card.u) {
      card.u = true
      Seq(CardRevealed(card))
    } else {
      SelectCardActions.playToFoundation(this, card, gameState)
    }
  }
}
