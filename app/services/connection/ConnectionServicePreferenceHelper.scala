package services.connection

import models._
import models.database.queries.auth.UserQueries
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import utils.cache.UserCache
import utils.metrics.InstrumentedActor

trait ConnectionServicePreferenceHelper extends InstrumentedActor { this: ConnectionService =>
  protected[this] def handleSetPreference(sp: SetPreference): Unit = {
    val newPrefs = sp.name match {
      case "card-back" => userPreferences.copy(cards = userPreferences.cards.copy(back = sp.value))
      case "card-layout" => userPreferences.copy(cards = userPreferences.cards.copy(layout = sp.value))
      case "card-rank" => userPreferences.copy(cards = userPreferences.cards.copy(ranks = sp.value))
      case "card-suit" => userPreferences.copy(cards = userPreferences.cards.copy(suits = sp.value))
      case "card-face" => userPreferences.copy(cards = userPreferences.cards.copy(faceCards = sp.value))
      case "auto-flip" => userPreferences.copy(autoFlip = sp.value.toBoolean)
      case "audio" => userPreferences.copy(audio = sp.value.toBoolean)
      case "gamepad" => userPreferences.copy(gamepad = sp.value.toBoolean)
      case "background-color" => userPreferences.copy(color = sp.value)
      case _ => log.errorThenThrow(s"Unhandled preference [${sp.name}] for user [$user.id] with value [${sp.value}].")
    }
    if (newPrefs != userPreferences) {
      Database.execute(UserQueries.SetPreferences(user.id, newPrefs)).map { x =>
        userPreferences = newPrefs
        UserCache.removeUser(user.id)
        activeGame.foreach { g =>
          g ! sp
        }
      }
    }
  }
}
