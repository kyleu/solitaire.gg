package services.game

import models.GameWon
import org.joda.time.DateTime
import services.user.UserStatisticsService
import play.api.libs.concurrent.Execution.Implicits.defaultContext

trait GameServiceCheatHelper { this: GameService =>
  private[this] val started = new DateTime()

  protected[this] def handleCheat(command: String) {
    command.split(" ").toList match {
      case h :: t if h == "cheat" =>
        cheat(t.headOption.getOrElse(throw new IllegalStateException()).trim, t.tail)
      case _ =>
        throw new IllegalStateException("Invalid command [].")
    }
  }

  private[this] def cheat(command: String, params: Seq[String]) = command match {
    case "win" => UserStatisticsService.getStatistics(player.userId).map { stats =>
      sendToAll("GameWon", GameWon(id, rules, firstForRules = false, firstForSeed = false, getResult, stats))
    }
    case _ => throw new IllegalArgumentException(s"Unknown cheat command [$command].")
  }
}
