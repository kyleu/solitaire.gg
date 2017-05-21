package services.test

import models._
import models.game.PossibleMove
import utils.Logging

import scala.util.Random

object GameSolver {
  val moveLimit = 5000
}

case class GameSolver(rules: String, testSeed: Int, gameSeed: Option[Int] = None) extends Logging {
  val rng = new Random(testSeed)

  var gameWon = false

  // StartGame(rules, gameSeed, testGame = Some(true))
  //val gameId = gameJoined.id
  var moves = Seq.empty[PossibleMove]
  var undosAvailable = 0

  val exploredStates = collection.mutable.HashMap.empty[Seq[PossibleMove], collection.mutable.HashMap[PossibleMove, Seq[PossibleMove]]]

  def onMsg(msg: ResponseMessage) = msg match {
    case rm: ResponseMessage =>
      rm match {
        case cr: CardRevealed => true
        case ch: CardHidden => true
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
        //conn ! Undo
        //while (onMsg()) { Unit }
        //results(PossibleMove("undo", Nil, "", None)) = moves
      }
      Undo
    } else {
      val move = if (rng.nextInt(10) < 7) {
        unexploredMoves.headOption.getOrElse(throw new IllegalStateException())
      } else {
        unexploredMoves(rng.nextInt(unexploredMoves.size))
      }
      val msg = move.t match {
        case PossibleMove.Type.MoveCards => MoveCards(move.cards, move.sourcePile, move.targetPile.getOrElse(throw new IllegalStateException()), auto = true)
        case PossibleMove.Type.SelectCard => SelectCard(move.cards.headOption.getOrElse(throw new IllegalStateException()), move.sourcePile, auto = true)
        case PossibleMove.Type.SelectPile => SelectPile(move.sourcePile, auto = true)
        case _ => throw new IllegalArgumentException(s"Invalid possible move [${move.t}].")
      }
      //log.info("Sending [" + msg + "].")
      //conn ! msg
      //while (onMsg()) { Unit }
      results(move) = moves
      msg
    }
  }
}
