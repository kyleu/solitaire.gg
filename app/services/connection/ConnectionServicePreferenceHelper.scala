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
      case "card-back" => user.preferences.copy(cards = user.preferences.cards.copy(back = sp.value))
      case "card-layout" => user.preferences.copy(cards = user.preferences.cards.copy(layout = sp.value))
      case "card-rank" => user.preferences.copy(cards = user.preferences.cards.copy(ranks = sp.value))
      case "card-suit" => user.preferences.copy(cards = user.preferences.cards.copy(suits = sp.value))
      case "card-face" => user.preferences.copy(cards = user.preferences.cards.copy(faceCards = sp.value))
      case "auto-flip" => user.preferences.copy(autoFlip = sp.value.toBoolean)
      case "background-color" => user.preferences.copy(color = sp.value)
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
