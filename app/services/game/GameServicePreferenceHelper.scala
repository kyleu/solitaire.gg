package services.game

import java.util.UUID

import models.database.queries.auth.UserQueries
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import utils.cache.CacheService

trait GameServicePreferenceHelper { this: GameService =>
  protected[this] def handleSetPreference(userId: UUID, name: String, value: String): Unit = {
    Database.query(UserQueries.getById(Seq(userId))).map {
      case Some(u) =>
        val newPrefs = name match {
          case "color" => u.preferences.copy(color = value)
          case "autoFlip" => u.preferences.copy(autoFlip = value.toBoolean)
          case "back" => u.preferences.copy(cards = u.preferences.cards.copy(back = value))
          case "layout" => u.preferences.copy(cards = u.preferences.cards.copy(layout = value))
          case "ranks" => u.preferences.copy(cards = u.preferences.cards.copy(ranks = value))
          case "suits" => u.preferences.copy(cards = u.preferences.cards.copy(suits = value))
          case "faceCards" => u.preferences.copy(cards = u.preferences.cards.copy(faceCards = value))
          case _ => log.errorThenThrow(s"Unhandled preference [$name] for user [$userId] with value [$value].")
        }
        Database.execute(UserQueries.SetPreferences(userId, newPrefs)).map { x =>
          CacheService.cacheUser(u.copy(preferences = newPrefs))
        }
      case None => throw new IllegalArgumentException(s"Cannot find user [$userId].")
    }
  }
}
