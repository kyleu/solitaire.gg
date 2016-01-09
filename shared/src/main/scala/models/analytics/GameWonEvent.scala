package models.analytics

import java.util.UUID

import models.GameWon

case class GameWonEvent(
  deviceId: UUID,
  sessionId: UUID,
  message: GameWon,
  requests: Seq[Seq[String]],
  occurred: Long
)
