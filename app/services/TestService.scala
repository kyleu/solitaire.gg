package services

import akka.testkit.TestProbe
import models.{PossibleMoves, GetPossibleMoves, GameJoined, StartGame}
import models.game.GameVariant
import play.api.libs.concurrent.Akka
import utils.Logging

import scala.util.{Failure, Success, Try}

object TestService extends Logging {
  private def runTest(test: String)(f: () => Seq[String]) = {
    val startMs = System.currentTimeMillis()
    log.debug("Running test [" + test + "].")
    val ret = Try {
      f()
    }
    ret match {
      case Success(result) =>
        val msg = "Test [" + test + "] completed successfully in [" + (System.currentTimeMillis - startMs) + "ms]."
        log.debug(msg)
        msg + "\n" + result.map(s => "  " + s).mkString("\n")
      case Failure(ex) =>
        val msg = "Test [" + test + "] failed with [" + ex.getClass.getSimpleName + "] in [" + (System.currentTimeMillis - startMs) + "ms]."
        log.warn(msg, ex)
        msg + "\n  " + ex.getMessage
    }
  }

  def testGame(variant: String) = runTest(variant + "-game") { () =>
    import play.api.Play.current
    implicit val system = Akka.system
    val testProbe = TestProbe()
    val conn = system.actorOf(ConnectionService.props(ActorSupervisor.instance, "test-user", testProbe.ref))
    conn ! StartGame(variant)
    val gameJoined = testProbe.expectMsgClass(classOf[GameJoined])
    conn ! GetPossibleMoves
    val moves = testProbe.expectMsgClass(classOf[PossibleMoves])
    Seq(
      "Started [" + variant + "] game [" + gameJoined.id + "].",
      "Received [" + moves.moves.size + "] possible moves:"
    ) ++ moves.moves.map(m => "  " + m.toString)
  }

  def testVariants() = GameVariant.all.map { v =>
    testGame(v.key)
  }.mkString("\n")

  def testAll() = Seq(
    testVariants()
  ).mkString("\n\n")
}
