package services.connection

import models._
import models.queries.user.UserQueries
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import utils.cache.UserCache
import utils.metrics.InstrumentedActor

trait ConnectionServicePreferenceHelper extends InstrumentedActor { this: ConnectionService =>
  protected[this] def handleSetPreference(sp: SetPreference): Unit = {
    val newPrefs = userPreferences.withNewValue(sp.name, sp.value)
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
