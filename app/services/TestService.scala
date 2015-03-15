package services

import akka.testkit.TestProbe
import models.game.variants.GameVariant
import models.{PossibleMoves, GetPossibleMoves, GameJoined, StartGame}
import play.api.libs.concurrent.Akka
import utils.Logging

import scala.util.{Failure, Success, Try}

object TestService extends Logging {
  case class TestResult(content: String, children: Seq[TestResult] = Nil) {
    def toString(indentionLevel: Int): String = {
      (0 to indentionLevel).map(x => "  ").mkString + content + "\n" + children.map(_.toString(indentionLevel + 1)).mkString
    }
  }

  private def runTest(test: String)(f: () => TestResult) = {
    val startMs = System.currentTimeMillis()
    log.debug("Running test [" + test + "].")
    val ret = Try {
      f()
    }
    ret match {
      case Success(result) =>
        val msg = "Test [" + test + "] completed successfully in [" + (System.currentTimeMillis - startMs) + "ms] with [" + result.content + "]."
        log.debug(msg)
        result
      case Failure(ex) =>
        val msg = "Test [" + test + "] failed with [" + ex.getClass.getSimpleName + "] in [" + (System.currentTimeMillis - startMs) + "ms]."
        log.warn(msg, ex)
        TestResult(msg, Seq(TestResult(ex.getMessage)))
    }
  }

  def testVariant(variant: String, verbose: Boolean = true) = runTest(variant + "-game") { () =>
    import play.api.Play.current
    implicit val system = Akka.system
    val testProbe = TestProbe()
    val conn = system.actorOf(ConnectionService.props(ActorSupervisor.instance, "test-user", testProbe.ref))
    val results = Seq({
      conn ! StartGame(variant)
      val gameJoined = testProbe.expectMsgClass(classOf[GameJoined])
      TestResult("Started [" + variant + "] game [" + gameJoined.id + "].", if(verbose) { gameJoined.state.toStrings.map(x => TestResult(x)) } else { Nil })
    }, {
      conn ! GetPossibleMoves
      val moves = testProbe.expectMsgClass(classOf[PossibleMoves])
      TestResult("Received [" + moves.moves.size + "] possible moves.", if(verbose) { moves.moves.map(x => TestResult(x.toString)) } else { Nil })
    })
    TestResult("Testing game variant [" + variant + "].", results)
  }

  def testVariants() = TestResult("Testing all game variants.", GameVariant.all.map { v =>
    testVariant(v.key, verbose = false)
  })

  def testAll() = TestResult("Running all tests.", Seq(
    testVariants()
  ))
}
