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
  var undosAvailable = 0

  val exploredStates = collection.mutable.HashMap.empty[Seq[PossibleMove], collection.mutable.HashMap[PossibleMove, Seq[PossibleMove]]]

  def onMsg() = testProbe.expectMsgPF() {
    case rm: ResponseMessage =>
      rm match {
        case cr: CardRevealed =>
          log.info("Received [" + rm + "].")
          true
        case ch: CardHidden =>
          log.info("Received [" + rm + "].")
          true
        case cm: CardMoved =>
          log.info("Received [" + cm + "].")
          true
        case cm: CardsMoved =>
          log.info("Received [" + cm + "].")
          true
        case ms: MessageSet =>
          log.info("Received [MessageSet(...)].")
          true
        case pm: PossibleMoves =>
          log.info("Received [PossibleMoves(" + pm.moves.size + ": " + pm.moves.hashCode() + ")].")
          moves = pm.moves
          undosAvailable = pm.undosAvailable
          false
        case _ => throw new IllegalStateException(rm.toString)
      }
  }

  def pointless(move: PossibleMove) = {
    move.sourcePile.startsWith("foundation")
  }

  def performMove() = {
    val results = exploredStates.getOrElseUpdate(moves, collection.mutable.HashMap.empty[PossibleMove, Seq[PossibleMove]])
    val unexploredMoves = moves.filter(x => !pointless(x) && !results.contains(x))
    if (unexploredMoves.isEmpty) {
      if (undosAvailable == 0) {
        throw new IllegalStateException("No undos available, no unexplored moves.")
      } else {
        log.info("Sending [Undo].")
        conn ! Undo
        while (onMsg()) { Unit }
        results(PossibleMove("undo", Nil, "", None)) = moves
      }
    } else {
      val move = moves(rng.nextInt(moves.size))
      val msg = move.moveType match {
        case "move-cards" => MoveCards(move.cards, move.sourcePile, move.targetPile.getOrElse(throw new IllegalStateException()))
        case "select-card" => SelectCard(move.cards.headOption.getOrElse(throw new IllegalStateException()), move.sourcePile)
        case "select-pile" => SelectPile(move.sourcePile)
        case _ => throw new IllegalArgumentException("Invalid possible move [" + move.moveType + "].")
      }
      log.info("Sending [" + msg + "].")
      conn ! msg
      while (onMsg()) { Unit }
      results(move) = moves
    }
  }
}
