package services.game

import java.util.UUID

import models.database.queries.auth.UserQueries
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import utils.cache.UserCache

trait GameServicePreferenceHelper { this: GameService =>
  protected[this] def handleSetPreference(userId: UUID, name: String, value: String): Unit = {
    Database.query(UserQueries.getById(Seq(userId))).map {
      case Some(u) =>
        val newPrefs = name match {
          case "card-back" => u.preferences.copy(cards = u.preferences.cards.copy(back = value))
          case "card-layout" => u.preferences.copy(cards = u.preferences.cards.copy(layout = value))
          case "card-rank" => u.preferences.copy(cards = u.preferences.cards.copy(ranks = value))
          case "card-suit" => u.preferences.copy(cards = u.preferences.cards.copy(suits = value))
          case "card-face" => u.preferences.copy(cards = u.preferences.cards.copy(faceCards = value))
          case "auto-flip" => u.preferences.copy(autoFlip = value.toBoolean)
          case "background-color" => u.preferences.copy(color = value)
          case _ => log.errorThenThrow(s"Unhandled preference [$name] for user [$userId] with value [$value].")
        }
        Database.execute(UserQueries.SetPreferences(userId, newPrefs)).map { x =>
          UserCache.removeUser(userId)
        }
      case None => throw new IllegalArgumentException(s"Cannot find user [$userId].")
    }
  }
}
