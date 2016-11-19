import java.util.UUID

import models.GameJoined
import models.rules.GameRulesSet
import models.rules.moves.InitialMoves
import models.user.UserPreferences

import scala.util.Random

trait StartGameHelper { this: MoveHelper =>
  private[this] val rng = new Random()

  protected[this] def handleStartGame(rules: String, seed: Option[Int], preferences: UserPreferences): Unit = {
    gameState.foreach(x => onLoss())
    clearRequests()

    val id = UUID.randomUUID
    gameId = Some(id)

    val gr = GameRulesSet.allByIdWithAliases(rules)
    gameRules = Some(gr)

    val actualSeed = seed.getOrElse(Math.abs(rng.nextInt()))

    val gs = gr.newGame(id, actualSeed, rules)

    gameStatus = "started"
    gameState = Some(gs)
    gs.addPlayer(DataHelper.deviceId, "Offline Player", autoFlipOption = preferences.autoFlip)
    InitialMoves.performInitialMoves(gameRules.getOrElse(throw new IllegalStateException()), gs)

    onGameStart(id, gr.id, actualSeed, System.currentTimeMillis)

    send(GameJoined(gameId.getOrElse(throw new IllegalStateException()), gs.view(DataHelper.deviceId), 0, possibleMoves(), preferences))
  }
}
