package models.game.rules.impl

import models.game.rules._

object SandboxB extends GameRules(
  id = "sandboxb",
  custom = true,
  title = "Sandbox B",
  layout = Some("s"),
  description = "Another work in progress...",
  cardRemovalMethod = CardRemovalMethod.BuildSequencesOnFoundation,

  waste = Some(WasteRules(
    cardsShown = 52
  ))
)
