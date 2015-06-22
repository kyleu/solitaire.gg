package services.test

import akka.testkit.TestProbe
import models._
import play.api.libs.concurrent.Akka
import services.{ ActorSupervisor, ConnectionService }

import play.api.Play.current
import utils.Logging

import scala.util.Random

object GameSolver {
  val moveLimit = 5000
}

case class GameSolver(rules: String, testSeed: Int, gameSeed: Option[Int] = None) extends Logging {
  val rng = new Random(testSeed)

  var gameWon = false

  implicit val system = Akka.system
  val testProbe = TestProbe()
  val conn = system.actorOf(ConnectionService.props(ActorSupervisor.instance, TestService.testUserId, "test-user", testProbe.ref))

  conn ! StartGame(rules, gameSeed, testGame = Some(true))
  val gameJoined = testProbe.expectMsgClass(classOf[GameJoined])
  val gameId = gameJoined.id
  var moves = gameJoined.moves
  var undosAvailable = 0

  val exploredStates = collection.mutable.HashMap.empty[Seq[PossibleMove], collection.mutable.HashMap[PossibleMove, Seq[PossibleMove]]]

  def onMsg() = testProbe.expectMsgPF() {
    case rm: ResponseMessage =>
      rm match {
        case cr: CardRevealed => true
        case ch: CardHidden => true
        case cm: CardMoved => true
        case cm: CardsMoved => true
        case ms: MessageSet => true
        case gw: GameWon =>
          gameWon = true
          false
        case pm: PossibleMoves =>
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
        //log.info("Sending [Undo].")
        conn ! Undo
        while (onMsg()) { Unit }
        results(PossibleMove("undo", Nil, "", None)) = moves
      }
      Undo
    } else {
      val move = if(rng.nextInt(10) < 7) {
        unexploredMoves.headOption.getOrElse(throw new IllegalStateException())
      } else {
        unexploredMoves(rng.nextInt(unexploredMoves.size))
      }
      val msg = move.moveType match {
        case "move-cards" => MoveCards(move.cards, move.sourcePile, move.targetPile.getOrElse(throw new IllegalStateException()))
        case "select-card" => SelectCard(move.cards.headOption.getOrElse(throw new IllegalStateException()), move.sourcePile)
        case "select-pile" => SelectPile(move.sourcePile)
        case _ => throw new IllegalArgumentException(s"Invalid possible move [${move.moveType}].")
      }
      //log.info("Sending [" + msg + "].")
      conn ! msg
      while (onMsg()) { Unit }
      results(move) = moves
      msg
    }
  }
}
