package services.test

import java.util.UUID

import akka.testkit.TestProbe
import models._
import play.api.libs.concurrent.Akka
import services.{ ActorSupervisor, ConnectionService }

import play.api.Play.current
import utils.Logging

import scala.util.Random

case class GameSolver(variant: String, testSeed: Int, gameSeed: Option[Int] = None) extends Logging {
  val rng = new Random(testSeed)

  implicit val system = Akka.system
  val testProbe = TestProbe()
  val accountId = UUID.randomUUID
  val conn = system.actorOf(ConnectionService.props(ActorSupervisor.instance, accountId, "test-user", testProbe.ref))

  conn ! StartGame(variant, gameSeed)
  val gameJoined = testProbe.expectMsgClass(classOf[GameJoined])
  val gameId = gameJoined.id
  var moves = gameJoined.moves

  def onMsg() = testProbe.expectMsgPF() {
    case cr: CardRevealed => true
    case cm: CardMoved => true
    case cm: CardsMoved => true
    case ms: MessageSet => true
    case pm: PossibleMoves =>
      moves = pm.moves
      false
  }

  def performMove() = {
    val move = moves(rng.nextInt(moves.size))
    move.moveType match {
      case "move-cards" =>
        conn ! MoveCards(move.cards, move.sourcePile, move.targetPile.getOrElse(throw new IllegalStateException()))
        while(onMsg()) {}
      case "select-card" =>
        conn ! SelectCard(move.cards.headOption.getOrElse(throw new IllegalStateException()), move.sourcePile)
        while(onMsg()) {}
      case "select-pile" =>
        conn ! SelectPile(move.sourcePile)
        while(onMsg()) {}
      case _ => throw new IllegalArgumentException("Invalid possible move [" + move.moveType + "].")
    }
  }
}
