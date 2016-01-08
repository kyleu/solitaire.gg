package models.analytics

import java.util.UUID

import models.GameWon

case class GameWonEvent(
  deviceId: UUID,
  message: GameWon,
  occurred: Long
)
