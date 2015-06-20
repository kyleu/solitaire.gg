package services.game

import java.util.UUID

trait GameServicePreferenceHelper { this: GameService =>
  protected[this] def handleSetPreference(userId: UUID, name: String, value: String) = name match {
    case _ => log.error(s"Unhandled preference [$name] for user [$userId] with value [$value].")
  }
}
