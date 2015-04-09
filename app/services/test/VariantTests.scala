package services.test

import java.util.UUID

import akka.actor.PoisonPill
import akka.testkit.TestProbe
import models.{ GetPossibleMoves, PossibleMoves, GameJoined, StartGame }
import models.game.variants.GameVariant
import play.api.libs.concurrent.Akka
import services.{ ActorSupervisor, ConnectionService }

import play.api.Play.current

trait VariantTests { this: TestService =>
  def testVariants() = runTest(() => TestResult("all-variants", GameVariant.all.map { v =>
    testVariant(v.key, verbose = false)
  }))

  def testVariant(variant: String, verbose: Boolean = true) = runTest { () =>
    implicit val system = Akka.system
    val testProbe = TestProbe()
    val accountId = UUID.randomUUID
    val conn = system.actorOf(ConnectionService.props(ActorSupervisor.instance, accountId, "test-user", testProbe.ref))
    val results = Seq({
      conn ! StartGame(variant)
      val gameJoined = testProbe.expectMsgClass(classOf[GameJoined])
      TestResult("Started [" + variant + "] game [" + gameJoined.id + "].", if (verbose) { gameJoined.state.toStrings.map(x => TestResult(x)) } else { Nil })
    }, {
      conn ! GetPossibleMoves
      val moves = testProbe.expectMsgClass(classOf[PossibleMoves])
      TestResult("Received [" + moves.moves.size + "] possible moves.", if (verbose) { moves.moves.map(x => TestResult(x.toString)) } else { Nil })
    })
    conn ! PoisonPill
    TestResult(variant, results)
  }
}
