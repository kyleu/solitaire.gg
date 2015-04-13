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
      TestResult("Started [" + variant + "] game [" + gameJoined.id + "].")
    }, {
      conn ! GetPossibleMoves
      moves = testProbe.expectMsgClass(classOf[PossibleMoves]).moves
      TestResult("Received [" + moves.size + "] possible moves.")
    }, {
      val action = MoveCards(moves.find(_.sourcePile == "tableau-1").getOrElse(throw new IllegalStateException()).cards, "tableau-1", "tableau-6")
      conn ! action
      val cardMoved = testProbe.expectMsgClass(classOf[CardsMoved])
      moves = testProbe.expectMsgClass(classOf[PossibleMoves]).moves
      TestResult("Performed [MoveCards], received [" + moves.size + "] possible moves.")
    }, {
      val action = MoveCards(moves.find(_.sourcePile == "tableau-6").getOrElse(throw new IllegalStateException()).cards, "tableau-6", "tableau-4")
      conn ! action
      val cardMoved = testProbe.expectMsgClass(classOf[CardsMoved])
      moves = testProbe.expectMsgClass(classOf[PossibleMoves]).moves
      TestResult("Performed [MoveCards], received [" + moves.size + "] possible moves.")
    })
    conn ! PoisonPill
    TestResult("known-game", results)
  }
}
