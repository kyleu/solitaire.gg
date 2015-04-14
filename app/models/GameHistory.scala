package models

import java.util.UUID

import org.joda.time.LocalDateTime

case class GameHistory(
  id: UUID,
  seed: Int,
  variant: String,
  status: String,
  accounts: Seq[UUID],
  moves: Int,
  undos: Int,
  redos: Int,
  created: LocalDateTime,
  completed: Option[LocalDateTime]
)
