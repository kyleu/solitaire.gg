package services.test

import java.util.UUID

import akka.actor.PoisonPill
import akka.testkit.TestProbe
import models._
import play.api.libs.concurrent.Akka
import services.{ ActorSupervisor, ConnectionService }

import play.api.Play.current

trait KnownGameTests { this: TestService =>
  def testKnownGame() = runTest { () =>
    val variant = "klondike"
    val seed = 5
    implicit val system = Akka.system
    val testProbe = TestProbe()
    val accountId = UUID.randomUUID
    val conn = system.actorOf(ConnectionService.props(ActorSupervisor.instance, accountId, "test-user", testProbe.ref))
    var moves = Seq.empty[PossibleMove]
    val results = Seq({
      conn ! StartGame("klondike", Some(seed))
      val gameJoined = testProbe.expectMsgClass(classOf[GameJoined])
      TestResult("Started [" + variant + "] game [" + gameJoined.id + "].", gameJoined.state.toStrings.map(x => TestResult(x)))
    }, {
      conn ! GetPossibleMoves
      moves = testProbe.expectMsgClass(classOf[PossibleMoves]).moves
      TestResult("Received [" + moves.size + "] possible moves.", moves.map(x => TestResult(x.toString)))
    }, {
      val action = MoveCards(moves.find(_.sourcePile == "tableau-1").getOrElse(throw new IllegalStateException()).cards, "tableau-1", "tableau-6")
      conn ! action
      val cardMoved = testProbe.expectMsgClass(classOf[CardMoved])
      moves = testProbe.expectMsgClass(classOf[PossibleMoves]).moves
      TestResult("Performed [" + action + "] with result [" + cardMoved + "], received [" + moves.size + "] possible moves.", moves.map { x =>
        TestResult(x.toString)
      })
    }, {
      val action = MoveCards(moves.find(_.sourcePile == "tableau-6").getOrElse(throw new IllegalStateException()).cards, "tableau-6", "tableau-4")
      conn ! action
      val cardMoved = testProbe.expectMsgClass(classOf[MessageSet])
      moves = testProbe.expectMsgClass(classOf[PossibleMoves]).moves
      TestResult("Performed [" + action + "] with result [" + cardMoved + "], received [" + moves.size + "] possible moves.", moves.map { x =>
        TestResult(x.toString)
      })
    })
    conn ! PoisonPill
    TestResult("known-game", results)
  }
}
