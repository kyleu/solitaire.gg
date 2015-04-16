import java.util.UUID

import models.game.GameState
import models.game.variants.GameVariant
import models.{ Pong, GameJoined, VersionResponse }

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

import scala.util.Random

import upickle._

object Scalataire extends js.JSApp {
  def main(): Unit = {
    println("Somehow, it works!")
  }

  @JSExport
  def register(callback: js.Function1[String, Unit]) = sendCallback = callback

  @JSExport
  def receive(c: String, v: js.Dynamic) = {
    println("Received [" + c + "].")
    c match {
      case "GetVersion" => send(write(VersionResponse("0.offline")))
      case "Ping" => send(write(Pong(JsonUtils.getLong(v.timestamp))))
      case "StartGame" => handleStartGame(v.variant.toString, JsonUtils.getIntOption(v.seed))
      case _ => throw new IllegalStateException("Invalid message [" + c + "].")
    }
  }

  private[this] var sendCallback: js.Function1[String, Unit] = _

  private[this] val accountId = UUID.randomUUID
  private[this] val rng = new Random()

  private[this] var gameId: UUID = _
  private[this] var gameVariant: GameVariant = _
  private[this] var gameState: GameState = _

  private[this] def send(v: String) = {
    println("Sending [" + v + "].")
    sendCallback(v)
  }

  private[this] def handleStartGame(variant: String, seed: Option[Int]) = {
    gameId = UUID.randomUUID
    gameVariant = GameVariant(variant, gameId, seed.getOrElse(Math.abs(rng.nextInt())))

    gameState = gameVariant.gameState
    gameState.addPlayer(accountId, "Offline Player")

    gameVariant.initialMoves()

    val msg = GameJoined(gameId, gameState.view(accountId), Nil)


    send(JsonUtils.writeGameJoined(msg))
  }
}
