package services.game

import java.util.UUID

import models.database.queries.auth.UserQueries
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import utils.CacheService

trait GameServicePreferenceHelper { this: GameService =>
  protected[this] def handleSetPreference(userId: UUID, name: String, value: String): Unit = name match {
    case "color" => Database.execute(UserQueries.SetColor(userId, value)).map(x => CacheService.removeUser(userId))
    case _ => log.error(s"Unhandled preference [$name] for user [$userId] with value [$value].")
  }
}
