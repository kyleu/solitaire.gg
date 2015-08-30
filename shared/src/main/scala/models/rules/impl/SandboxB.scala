package models.rules.impl

import models.rules._

object SandboxB extends GameRules(
  id = "sandboxb",
  completed = false,
  title = "Sandbox B",
  layout = "s",
  description = "Another work in progress...",
  cardRemovalMethod = CardRemovalMethod.BuildSequencesOnFoundation,

  waste = Some(WasteRules(
    cardsShown = 52
  ))
)