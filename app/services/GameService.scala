package services

import akka.actor.{Props, ActorRef}
import models.game.{Pile, Deck, GameState}
import models.{GameJoined, JoinGame}
import play.api.Logger
import utils.metrics.InstrumentedActor

import scala.util.Random

object GameService {
  private def props(gameType: String, seed: Int, players: List[String], connections: Map[String, ActorRef]) = Props(new GameService(gameType, seed, players, connections))
  private def masterRng = new Random()

  def joinGame(out: ActorRef, jg: JoinGame, seed: Int = Math.abs(masterRng.nextInt())) = {
    props(jg.game, seed, List(jg.name), Map(jg.name -> out))
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
    case x => Logger.warn("GameService received unknown message [" + x.getClass.getSimpleName + "].")
  }
}
