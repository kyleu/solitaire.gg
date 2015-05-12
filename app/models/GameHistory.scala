package models

import java.util.UUID

import org.joda.time.LocalDateTime

case class GameHistory(
  id: UUID,
  seed: Int,
  rules: String,
  status: String,
  player: UUID,
  moves: Int,
  undos: Int,
  redos: Int,
  created: LocalDateTime,
  completed: Option[LocalDateTime]
)
