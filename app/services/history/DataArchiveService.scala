package services.history

import models.database.queries.history.DataArchiveQueries
import models.history.DataArchiveCount
import org.joda.time.{ LocalDateTime, LocalDate }
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import utils.DateUtils

import scala.concurrent.Future

object DataArchiveService {
  val tables = Seq(
    ("Games", "games"),
    ("Game Moves", "game_moves"),
    ("Game Cards", "game_cards"),
    ("Requests", "requests"),
    ("Users", "users")
  )

  def getAll = Database.query(DataArchiveQueries.getAll)
  def getByDay(day: LocalDate) = Database.query(DataArchiveQueries.GetArchiveCountsByDay(day))

  def process() = {
    val today = DateUtils.today

    getByDay(today).flatMap { currentArchiveCounts =>
      val currentByTable = currentArchiveCounts.map(x => x.table -> x).toMap

      val userCount = Database.query(DataArchiveQueries.GetUsersWithoutGames).map { users =>
        val currentUserCount = currentByTable.get("users").map(_.count).getOrElse(0)
        DataArchiveCount("users", today, currentUserCount + users.size, new LocalDateTime())
      }

      Future.sequence(Seq(userCount)).flatMap { counts =>
        Database.execute(DataArchiveQueries.RemoveByDay(today)).flatMap { _ =>
          Database.execute(DataArchiveQueries.insertBatch(counts)).map { _ =>
            counts
          }
        }
      }
    }
  }
}
