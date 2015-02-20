package services

import akka.actor.{Props, ActorRef}
import models.game.{Pile, Deck, GameState}
import models.{GameJoined, JoinGame}
import play.api.Logger
import utils.metrics.InstrumentedActor

object GameService {
  private def props(gameType: String, players: List[String], connections: Map[String, ActorRef]) = Props(new GameService(gameType, players, connections))

  def joinGame(out: ActorRef, jg: JoinGame) = {
    props(jg.game, List(jg.name), Map(jg.name -> out))
  }
}

class GameService(gameType: String, players: List[String], connections: Map[String, ActorRef]) extends InstrumentedActor {
  connections.values.foreach { c =>
    val gameState = gameType match {
      case "klondike" => GameState.klondike(Deck.fresh)
      case "sandbox" => GameState.sandbox(Deck.fresh)
      case _ => throw new IllegalArgumentException("Unknown game type: [" + gameType + "].")
    }
    c ! GameJoined(Nil, gameState)
  }

  override def receiveRequest = {
    case x => Logger.warn("GameService received unknown message [" + x.getClass.getSimpleName + "].")
  }
}
