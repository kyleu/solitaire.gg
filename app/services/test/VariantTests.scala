package services.test

import java.util.UUID

import akka.actor.PoisonPill
import akka.testkit.TestProbe
import models.rules.GameRulesSet
import models.test.{ Test, Tree }
import models.{ GetPossibleMoves, PossibleMoves, GameJoined, StartGame }
import play.api.libs.concurrent.Akka

import play.api.Play.current
import services.connection.ConnectionService
import services.supervisor.ActorSupervisor

class VariantTests {
  val all = Tree(Test("variant"), GameRulesSet.all.map(x => testVariant(x.id).toTree))

  def testVariant(rules: String) = Test(s"variant-$rules", () => {
    implicit val system = Akka.system
    val testProbe = TestProbe()
    val conn = system.actorOf(ConnectionService.props(ActorSupervisor.instance, TestService.testUser, testProbe.ref))
    var msg = "Unknown Result"
    conn ! StartGame(rules, None, testGame = Some(true))
    val gameJoined = testProbe.expectMsgClass(classOf[GameJoined])
    conn ! GetPossibleMoves
    val moves = testProbe.expectMsgClass(classOf[PossibleMoves])
    conn ! PoisonPill
    s"Received [${moves.moves.size}] possible moves."
  })
}
