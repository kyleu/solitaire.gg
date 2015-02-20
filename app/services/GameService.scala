package services

import akka.actor.{Props, ActorRef}
import models.game.{Pile, Deck, GameState}
import models.{GameJoined, JoinGame}
import play.api.Logger
import utils.metrics.InstrumentedActor

object GameService {
  private def props(players: List[String], connections: Map[String, ActorRef]) = Props(new GameService(players, connections))

  def joinGame(out: ActorRef, jg: JoinGame) = {
    props(List(jg.name), Map(jg.name -> out))
  }
}

class GameService(players: List[String], connections: Map[String, ActorRef]) extends InstrumentedActor {
  connections.values.foreach { c =>
    val gameState = GameState.default(Deck.fresh.copy(), Pile.default)
    c ! GameJoined(Nil, gameState)
  }

  override def receiveRequest = {
    case x => Logger.warn("GameService received unknown message [" + x.getClass.getSimpleName + "].")
  }
}
