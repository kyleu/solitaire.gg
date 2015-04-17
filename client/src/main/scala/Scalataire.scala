import java.util.UUID

import models.game.GameState
import models.game.variants.GameVariant
import models._
import upickle.Js

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

import scala.util.Random

object Scalataire extends js.JSApp with ScalataireHelper {
  protected[this] var gameId: UUID = _

  private[this] var sendCallback: js.Function1[String, Unit] = _

  private[this] val accountId = UUID.randomUUID
  private[this] val rng = new Random()

  protected[this] var gameVariant: GameVariant = _
  protected[this] var gameState: GameState = _

  def main() = Unit

  @JSExport
  def register(callback: js.Function1[String, Unit]) = sendCallback = callback

  @JSExport
  def receive(c: String, v: js.Dynamic) = {
    c match {
      case "GetVersion" => send(JsonUtils.write(VersionResponse("0.offline")))
      case "Ping" => send(JsonUtils.write(Pong(JsonUtils.getLong(v.timestamp))))
      case "StartGame" => handleStartGame(v.variant.toString, JsonUtils.getIntOption(v.seed))
      case "SelectCard" => handleSelectCard(accountId, UUID.fromString(v.card.toString), v.pile.toString)
      case "SelectPile" => handleSelectPile(accountId, v.pile.toString)
      case "MoveCards" => handleMoveCards(accountId, JsonUtils.getUuidSeq(v.cards), v.src.toString, v.tgt.toString)
      case _ => throw new IllegalStateException("Invalid message [" + c + "].")
    }
  }

  protected[this] def send(v: Js.Value) = {
    val json = JsonUtils.write(v)
    sendCallback(json)
  }

  private[this] def handleStartGame(variant: String, seed: Option[Int]) = {
    gameId = UUID.randomUUID
    gameVariant = GameVariant(variant, gameId, seed.getOrElse(Math.abs(rng.nextInt())))

    gameState = gameVariant.gameState
    gameState.addPlayer(accountId, "Offline Player")

    gameVariant.initialMoves()

    val msg = GameJoined(gameId, gameState.view(accountId), possibleMoves())

    send(JsonUtils.write(msg))
  }
}
