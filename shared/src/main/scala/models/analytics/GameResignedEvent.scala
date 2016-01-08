package models.analytics

import java.util.UUID

import models.GameLost

case class GameResignedEvent(
  deviceId: UUID,
  message: GameLost,
  occurred: Long
)
