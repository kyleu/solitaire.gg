package models.game.rules.custom

import models.game.rules._

object SandboxB extends GameRules(
  id = "sandboxb",
  title = "Sandbox B",
  description = "Another work in progress...",
  cardRemovalMethod = CardRemovalMethod.BuildSequencesOnFoundation,

  waste = Some(WasteRules(
    cardsShown = 52
  ))
)
