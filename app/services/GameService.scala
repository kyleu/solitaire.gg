package services

import akka.actor.{Props, ActorRef}
import models.game.{Pile, Deck, GameState}
import models._
import play.api.Logger
import utils.metrics.InstrumentedActor

import scala.util.Random

object GameService {
  private def props(gameType: String, seed: Int, players: List[String], connections: Map[String, ActorRef]) = Props(new GameService(gameType, seed, players, connections))
  private def masterRng = new Random()

  def joinGame(out: ActorRef, gameId: String, username: String, seed: Int = Math.abs(masterRng.nextInt())) = {
    props(gameId, seed, List(username), Map(username -> out))
  }
}

class GameService(gameType: String, seed: Int, players: List[String], connections: Map[String, ActorRef]) extends InstrumentedActor {
  private val rng = new Random(new java.util.Random(seed))

  private val gameState = gameType match {
    case "klondike" => GameState.klondike(Deck.shuffled(rng))
    case "sandbox" => GameState.sandbox(Deck.shuffled(rng))
    case _ => throw new IllegalArgumentException("Unknown game type: [" + gameType + "].")
  }

  connections.values.foreach { c =>
    c ! GameJoined(Nil, gameState)
  }

  override def receiveRequest = {
    case gr: GameRequest =>
      Logger.debug("Handling [" + gr.request.getClass.getSimpleName + "] message from user [" + gr.username + "].")
      gr.request match {
        case sc: SelectCard => handleSelectCard(sc.card)
        case r => Logger.warn("GameService received unknown game message [" + r.getClass.getSimpleName + "].")
      }
    case x => Logger.warn("GameService received unknown message [" + x.getClass.getSimpleName + "].")
  }

  def handleSelectCard(cardId: String) = {
    //val card = gameState.cards(cardId)
    //sendToAll(RevealCard())
  }

  private def sendToAll(rm: ResponseMessage) = for(conn <- connections) {
    conn._2 ! rm
  }
}
