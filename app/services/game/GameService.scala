package services.game

import java.util.UUID

import models._
import models.rules.GameRulesSet
import models.rules.moves.InitialMoves
import models.user.PlayerRecord
import org.joda.time.LocalDateTime

case class GameService(
    id: UUID, rules: String, seed: Int, started: LocalDateTime, protected val player: PlayerRecord, protected val testGame: Boolean
) extends GameServiceHelper {
  log.info(s"Started game [$rules] for user [${player.userId}: ${player.name}] with seed [$seed].")

  protected[this] val observerConnections = collection.mutable.ArrayBuffer.empty[(PlayerRecord, Option[UUID])]

  protected[this] val gameRules = GameRulesSet.allByIdWithAliases(rules)
  protected[this] val gameState = gameRules.newGame(id, seed, rules)

  gameState.addPlayer(player.userId, player.name, player.autoFlipOption)

  protected[this] val gameMessages = collection.mutable.ArrayBuffer.empty[(GameMessage, UUID, LocalDateTime)]
  protected[this] var moveCount = 0
  protected[this] var firstMoveMade: Option[LocalDateTime] = None
  protected[this] var lastMoveMade: Option[LocalDateTime] = None

  protected[this] var autoFlipOption = false

  protected[this] var status = "started"

  protected[this] def getResult = GameResult(
    moves = moveCount,
    undos = undoHelper.undoCount,
    redos = undoHelper.redoCount,
    score = gameState.calculateScore(moveCount, undoHelper.undoCount, elapsed.getOrElse(0)),
    durationSeconds = elapsed.getOrElse(0),
    leaderboardRanking = 0
  )

  override def preStart() {
    this.create()
    InitialMoves.performInitialMoves(gameRules, gameState)

    player.connectionActor.foreach(_ ! GameStarted(id, self, started))
    player.connectionActor.foreach(_ ! GameJoined(id, gameState.view(player.userId), 0, possibleMoves(Some(player.userId))))
  }

  override def receiveRequest = {
    case gr: GameRequest => handleGameRequest(gr)
    case im: InternalMessage => handleInternalMessage(im)
    case di: DebugInfo => handleCheat(di.data)
    case sp: SetPreference => handleSetPreference(sp.name, sp.value)
    case x => log.warn(s"GameService received unknown message [${x.getClass.getSimpleName}].")
  }
}
