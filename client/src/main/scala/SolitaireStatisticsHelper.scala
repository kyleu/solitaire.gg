import models.user.UserStatistics
import org.scalajs.dom
import upickle.json
import upickle.legacy._

object SolitaireStatisticsHelper {
  implicit val statsReader = Reader[UserStatistics] { case s => readJs(s) }
  implicit val statsWriter = Writer[UserStatistics] { case s => writeJs(s) }

  lazy val defaultStats = UserStatistics(java.util.UUID.randomUUID(), 0L, UserStatistics.Games())
}

trait SolitaireStatisticsHelper {
  import SolitaireStatisticsHelper._

  private var statistics = readStatistics()

  def registerGame() = {
    statistics
  }

  def readStatistics() = {
    Option(dom.localStorage.getItem("user.statistics")) match {
      case Some(statsText) => try {
        read[UserStatistics](statsText)
      } catch {
        case x: Exception =>
          println("Malformed JSON for UserPreferences: [" + statsText + "].")
          saveStatistics(defaultStats)
          defaultStats
      }
      case None =>
        saveStatistics(defaultStats)
        defaultStats
    }
  }

  def saveStatistics(s: UserStatistics) = {
    val js = statsWriter.write(s)
    dom.localStorage.setItem("user.statistics", json.write(js))
  }
}
