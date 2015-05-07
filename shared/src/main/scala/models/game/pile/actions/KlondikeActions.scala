package models.game.pile.actions

object KlondikeActions {
  val flip = SelectCardAction("flip", (pile, card, gameState) => {
    if (!card.u) {
      card.u = true
      gameState.revealCardToAll(card)
    } else {
      Nil
    }
  })
}
